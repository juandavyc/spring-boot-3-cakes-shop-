package com.juandavyc.bakery.dto.product.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoriesUpdateRequestDTO {

    @NotEmpty
    private Set<Integer> categoryIds;

    private Integer userId;



}
