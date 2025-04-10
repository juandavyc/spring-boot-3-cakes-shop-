package com.juandavyc.bakery.controller;

import com.juandavyc.bakery.dto.page.response.PageResponse;
import com.juandavyc.bakery.dto.user.request.UserCreateRequestDTO;
import com.juandavyc.bakery.dto.user.request.UserUpdateRequestDTO;
import com.juandavyc.bakery.dto.user.response.UserCreatedResponseDTO;
import com.juandavyc.bakery.dto.user.response.UserResponseDTO;
import com.juandavyc.bakery.service.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Validated
@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<PageResponse<UserResponseDTO>> getUsersPage(
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(userService.getUsersPage(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<PageResponse<UserResponseDTO>> search(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @PageableDefault(size = 9, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                userService.search(
                        id,
                        username,
                        email,
                        pageable)
        );
    }

    @PostMapping()
    public ResponseEntity<UserCreatedResponseDTO> create(
            @Valid @RequestBody UserCreateRequestDTO user
    ) {
        final var userCreated = userService.create(user);

        return ResponseEntity
                .created(URI.create("/api/users/" + userCreated.id()))
                .body(userCreated);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UserUpdateRequestDTO user
    ) {
        final var userUpdated = userService.update(id, user);
        return ResponseEntity.ok(userUpdated);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

