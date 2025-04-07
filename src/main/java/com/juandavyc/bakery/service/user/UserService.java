package com.juandavyc.bakery.service.user;

import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.dto.user.request.UserCreateRequestDTO;
import com.juandavyc.bakery.dto.user.request.UserUpdateRequestDTO;
import com.juandavyc.bakery.dto.user.response.UserResponseDTO;

import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponseDTO create(UserCreateRequestDTO user);
    PageResponse<UserResponseDTO> getUsersPage(Pageable pageable);
    UserResponseDTO getUserById(Integer id);

    void deleteById(Integer id);

    UserResponseDTO update(Integer id, UserUpdateRequestDTO user);

}
