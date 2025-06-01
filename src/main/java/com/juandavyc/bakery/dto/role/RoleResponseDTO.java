package com.juandavyc.bakery.dto.role;

import com.juandavyc.bakery.dto.permission.response.PermissionResponseDTO;

import java.util.Set;

public record RoleResponseDTO(
        Long id,
        String name,
        Set<PermissionResponseDTO> permissions
) {
}
