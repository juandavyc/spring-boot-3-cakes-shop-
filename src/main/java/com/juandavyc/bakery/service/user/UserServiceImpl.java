package com.juandavyc.bakery.service.user;

import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.dto.user.request.UserCreateRequestDTO;
import com.juandavyc.bakery.dto.user.request.UserUpdateRequestDTO;
import com.juandavyc.bakery.dto.user.response.UserCreatedResponseDTO;
import com.juandavyc.bakery.dto.user.response.UserResponseDTO;
import com.juandavyc.bakery.entity.UserEntity;
import com.juandavyc.bakery.mapper.page.PageMapper;
import com.juandavyc.bakery.mapper.user.UserMapper;
import com.juandavyc.bakery.repository.UserRepository;
import com.juandavyc.bakery.specification.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PageMapper pageMapper;


    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public PageResponse<UserResponseDTO> getUsersPage(Pageable pageable) {

        final var users = userRepository.findAll(pageable);
        if (users.isEmpty()) {
            throw new IllegalArgumentException("No users found");
        }
        return pageMapper.toPageResponse(users, userMapper::userEntityToUserResponseDTO);
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public UserResponseDTO getUserById(Integer id) {
        final var user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        return userMapper.userEntityToUserResponseDTO(user);
    }

    @Override
    public PageResponse<UserResponseDTO> search(
            Integer id,
            String username,
            String email,
            Pageable pageable) {

        Specification<UserEntity> specification = Specification
                .where(UserSpecifications.hasId(id))
                .and(UserSpecifications.hasUsername(username))
                .and(UserSpecifications.hasEmail(email));

        final var users = userRepository.findAll(specification, pageable);

        return pageMapper.toPageResponse(users, userMapper::userEntityToUserResponseDTO);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public UserCreatedResponseDTO create(UserCreateRequestDTO user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        final var entity = userMapper.userRequestDTOToUserEntity(user);

        return userMapper.userEntityToUserCreatedResponseDTO(
                userRepository.save(entity)
        );
    }


    @Override
    public UserResponseDTO update(Integer id, UserUpdateRequestDTO user) {

        final var userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));

        userMapper.updateUserFromDto(user, userEntity);

        return userMapper.userEntityToUserResponseDTO(userRepository.save(userEntity));
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {

        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);

    }

}
