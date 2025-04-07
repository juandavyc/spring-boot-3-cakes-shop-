package com.juandavyc.bakery.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class UserUpdateRequestDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 3, max = 225, message = "Password must be between 3 and 225 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email
    @Size(min = 3, max = 225, message = "Email must be between 3 and 225 characters")
    private String email;

}
