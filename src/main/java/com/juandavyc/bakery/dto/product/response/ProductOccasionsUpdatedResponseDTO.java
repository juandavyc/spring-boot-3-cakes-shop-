package com.juandavyc.bakery.dto.product.response;

import com.juandavyc.bakery.dto.productoccasion.ProductOccasionResponseDTO;

import java.util.Set;
import java.util.UUID;

public record ProductOccasionsUpdatedResponseDTO(
       UUID id,
       String name,
       String slug,
       Set<ProductOccasionResponseDTO> productOccasions
) {
}
