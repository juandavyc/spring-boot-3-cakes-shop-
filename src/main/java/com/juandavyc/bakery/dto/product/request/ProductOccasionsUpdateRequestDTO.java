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
public class ProductOccasionsUpdateRequestDTO {

    @NotEmpty
    private Set<Integer> occasionsIds;

    private Integer userId;



}
