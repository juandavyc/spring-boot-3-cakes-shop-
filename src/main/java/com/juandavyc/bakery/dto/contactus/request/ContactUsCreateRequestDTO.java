package com.juandavyc.bakery.dto.contactus.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class ContactUsCreateRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 90 characters")
    private String name;

    @NotBlank(message = "PhoneNumber is required")
    @Size(min = 3, max = 50, message = "PhoneNumber must be between 3 and 50 characters")
    private String phoneNumber;

    @NotBlank(message = "Message is required")
    @Size(min = 3, max = 300, message = "Message must be between 3 and 300 characters")
    private String message;

}
