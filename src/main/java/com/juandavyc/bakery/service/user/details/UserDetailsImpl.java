package com.juandavyc.bakery.service.user.details;


import com.juandavyc.bakery.repository.UserRepository;
import com.juandavyc.bakery.security.LoginUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public LoginUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final var userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Set<GrantedAuthority> authorities = new HashSet<>(
                userEntity.getRoles()
                        .stream()
                        .map(roleEntity -> new SimpleGrantedAuthority(
                                "ROLE_".concat(roleEntity.getName().name()))
                        )
                        .collect(Collectors.toSet())
        );

        userEntity.getRoles().stream()
                .flatMap(roleEntity -> roleEntity.getPermissions().stream())
                .forEach(permissionEntity ->
                        authorities.add(
                                new SimpleGrantedAuthority(permissionEntity.getName().name())
                        )
                );

        return new LoginUserDetails(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities,
                userEntity.isAccountNoExpired(),
                userEntity.isAccountNoLocked(),
                userEntity.isCredentialNoExpired(),
                userEntity.isEnabled()
        );
    }
}
