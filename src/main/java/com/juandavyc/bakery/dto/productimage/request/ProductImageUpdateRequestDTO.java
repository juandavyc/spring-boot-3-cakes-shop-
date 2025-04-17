package com.juandavyc.bakery.dto.productimage.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class ProductImageUpdateRequestDTO {

    @NotBlank
    private Integer id;

    @NotBlank
    private String url;

    @NotBlank
    private String altText;

}
