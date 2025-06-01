package com.juandavyc.bakery.service.auth;

import com.juandavyc.bakery.dto.auth.request.AuthRequestDTO;
import com.juandavyc.bakery.dto.auth.response.AuthResponseDTO;
import com.juandavyc.bakery.dto.auth.response.AuthUserResponseDTO;
import com.juandavyc.bakery.dto.auth.response.TokenResponseDTO;
import com.juandavyc.bakery.mapper.auth.AuthMapper;
import com.juandavyc.bakery.security.LoginUserDetails;
import com.juandavyc.bakery.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthMapper authMapper;


    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();

        String accessToken = jwtService.getToken(userDetails);
        String refreshToken = jwtService.getRefreshToken(userDetails);

//        return new AuthResponseDTO(userDetails,accessToken,refreshToken);
        return authMapper.toAuthResponseDTO(userDetails,accessToken,refreshToken);

    }

    public TokenResponseDTO refresh(String refreshToken) {

        String username = jwtService.getUsernameFromToken(refreshToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isValid(refreshToken)) {
            String newAccessToken = jwtService.getToken(userDetails);
            return new TokenResponseDTO(newAccessToken, refreshToken);
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    @Override
    public AuthUserResponseDTO me(String token) {
        System.out.println("token: "+token);
        String username = jwtService.getUsernameFromToken(token);

        LoginUserDetails userDetails = (LoginUserDetails) userDetailsService.loadUserByUsername(username);


        return authMapper.toAuthUserResponseDTO(userDetails);

//        if (jwtService.isValid(token)) {
//
//
//        } else {
//            throw new RuntimeException("Invalid refresh token");
//        }

    }

}
