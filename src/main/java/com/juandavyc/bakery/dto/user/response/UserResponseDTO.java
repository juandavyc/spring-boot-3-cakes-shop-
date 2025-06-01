package com.juandavyc.bakery.dto.user.response;


import com.juandavyc.bakery.dto.permission.response.PermissionResponseDTO;
import com.juandavyc.bakery.dto.role.RoleResponseDTO;
import jakarta.persistence.Column;

import java.util.Set;

public record UserResponseDTO(
        Integer id,
        String username,
        String lastname,
        String firstname,
        String email,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        Set<RoleResponseDTO> roles
) {

}
