package com.juandavyc.bakery.dto.auth.response;

import java.util.Set;

public record AuthUserResponseDTO(
        Integer id,
        String username,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        Set<String> roles,
        Set<String> permissions
) {
}
