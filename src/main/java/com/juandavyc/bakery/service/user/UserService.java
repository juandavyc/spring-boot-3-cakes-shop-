package com.juandavyc.bakery.service.user;

import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.dto.user.request.UserCreateRequestDTO;
import com.juandavyc.bakery.dto.user.request.UserUpdateRequestDTO;
import com.juandavyc.bakery.dto.user.response.UserCreatedResponseDTO;
import com.juandavyc.bakery.dto.user.response.UserResponseDTO;

import org.springframework.data.domain.Pageable;

public interface UserService {


    PageResponse<UserResponseDTO> getUsersPage(Pageable pageable);

    UserResponseDTO getUserById(Integer id);

    PageResponse<UserResponseDTO> search(
            Integer id,
            String username,
            String email,
            Pageable pageable);

    UserCreatedResponseDTO create(UserCreateRequestDTO user);

    UserResponseDTO update(Integer id, UserUpdateRequestDTO user);

    default void deleteById(Integer id) {

    }

}
