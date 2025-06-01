package com.juandavyc.bakery.mapper.auth;

import com.juandavyc.bakery.dto.auth.response.AuthResponseDTO;
import com.juandavyc.bakery.dto.auth.response.TokenResponseDTO;

import com.juandavyc.bakery.dto.auth.response.AuthUserResponseDTO;
import com.juandavyc.bakery.security.LoginUserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthMapper {


//    @Mapping(target = "roles", expression = "java(extractRoles(userDetails))")
//    @Mapping(target = "permissions", expression = "java(extractPermissions(userDetails))")
    @Mapping(target = "user", expression = "java(userResponse(userDetails))")
    @Mapping(target = "token", expression="java(tokenResponse(token, refreshToken))")
    AuthResponseDTO toAuthResponseDTO(LoginUserDetails userDetails, String token, String refreshToken);

    @Mapping(target = "roles", expression = "java(extractRoles(userDetails))")
    @Mapping(target = "permissions", expression = "java(extractPermissions(userDetails))")
    AuthUserResponseDTO toAuthUserResponseDTO(LoginUserDetails userDetails);


    default AuthUserResponseDTO userResponse(LoginUserDetails userDetails){
        final var roles = extractRoles(userDetails);
        final var permissions = extractPermissions(userDetails);

        return new AuthUserResponseDTO(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.isAccountNonExpired(),
                userDetails.isAccountNonLocked(),
                userDetails.isCredentialsNonExpired(),
                userDetails.isEnabled(),
                roles,
                permissions
        );
    }

    default TokenResponseDTO tokenResponse(String token, String refreshToken){
        return new TokenResponseDTO(token, refreshToken);
    }

    default Set<String> extractRoles(LoginUserDetails userDetails){
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority->authority.startsWith("ROLE_"))
                .collect(Collectors.toSet());
    }

    default Set<String> extractPermissions(LoginUserDetails userDetails){
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority->!authority.startsWith("ROLE_"))
                .collect(Collectors.toSet());
    }

}
