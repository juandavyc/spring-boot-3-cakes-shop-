package com.juandavyc.bakery.dto.product.response;

import com.juandavyc.bakery.dto.productimage.response.ProductImageResponseDTO;
import com.juandavyc.bakery.dto.productoccasion.ProductOccasionResponseDTO;

import java.util.Set;
import java.util.UUID;

public record ProductImagesUpdatedResponseDTO(
        UUID id,
        String name,
        String slug,
        Set<ProductImageResponseDTO> productImages

) {
}
