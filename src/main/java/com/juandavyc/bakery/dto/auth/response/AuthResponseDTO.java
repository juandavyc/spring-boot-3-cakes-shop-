package com.juandavyc.bakery.dto.auth.response;

public record AuthResponseDTO(
        AuthUserResponseDTO user,
        TokenResponseDTO token
) {
}
