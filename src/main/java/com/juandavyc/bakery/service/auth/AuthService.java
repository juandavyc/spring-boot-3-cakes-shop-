package com.juandavyc.bakery.service.auth;

import com.juandavyc.bakery.dto.auth.request.AuthRequestDTO;
import com.juandavyc.bakery.dto.auth.response.AuthResponseDTO;
import com.juandavyc.bakery.dto.auth.response.AuthUserResponseDTO;
import com.juandavyc.bakery.dto.auth.response.TokenResponseDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO request);

    TokenResponseDTO refresh(String refreshToken);

    AuthUserResponseDTO me(String token);
}
