package com.juandavyc.bakery.controller.auth;
import com.juandavyc.bakery.dto.auth.request.AuthRequestDTO;
import com.juandavyc.bakery.dto.auth.request.RefreshTokenRequest;
import com.juandavyc.bakery.dto.auth.response.AuthResponseDTO;
import com.juandavyc.bakery.dto.auth.response.AuthUserResponseDTO;
import com.juandavyc.bakery.dto.auth.response.TokenResponseDTO;
import com.juandavyc.bakery.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody AuthRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refresh(request.getRefreshToken()));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthUserResponseDTO> me(@RequestHeader("Authorization") String authorizationHeader) {

        String token = null;

        if(authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        return ResponseEntity.ok(authService.me(token));
    }

}
