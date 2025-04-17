package com.juandavyc.bakery.dto.product.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class ProductUpdateRequestDTO {


    @Size(min = 3,max = 90,message = "name must be between 3 or 90 characters")
    private String name;

    @Size(min = 3,max = 600,message = "name must be between 3 or 600 characters")
    private String description;

    @Min(value = 2000, message = "price must be greater than 2000")
    @Max(value = 1000000, message = "price must be less than 1000000")
    private BigInteger price;

    @Size(min = 3,max = 255,message = "name must be between 3 or 255 characters")
    private String cover;

    @NotNull(message = "User is required")
    private Integer userId;

}
