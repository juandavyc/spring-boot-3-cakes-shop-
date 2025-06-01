package com.juandavyc.bakery.dto.auth.response;

public record TokenResponseDTO (
        String accessToken,
        String refreshToken
){

}
