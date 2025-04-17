package com.juandavyc.bakery.dto.product.request;

import com.juandavyc.bakery.dto.productimage.request.ProductImageCreateRequestDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Set;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequestDTO {

    @NotBlank(message = "name is required")
    @Size(min = 3,max = 90,message = "name must be between 3 or 90 characters")
    private String name;

    @NotNull(message = "description is required")
    @Size(min = 3,max = 600,message = "name must be between 3 or 600 characters")
    private String description;

    @NotNull(message = "price is required")
    @Min(value = 2000, message = "price must be greater than 2000")
    @Max(value = 1000000, message = "price must be less than 2000")
    private BigInteger price;

    @NotBlank(message = "cover is required")
    private String cover;

//    @NotEmpty(message = "Category is required")
//    @Size(min = 1, max = 4, message = "Must be minimum 1 or 4 categories maximum")
    private Set<Integer> categoryIds;

//    @NotEmpty(message = "Occasion is required")
//    @Size(min = 1, max = 4, message = "Must be minimum 1 or 4 occasions maximum")
    private Set<Integer> occasionIds;

    private Set<ProductImageCreateRequestDTO> productImages;

    @NotNull(message = "User is required")
    private Integer userId;

}
