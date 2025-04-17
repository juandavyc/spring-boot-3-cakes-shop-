package com.juandavyc.bakery.dto.product.response;

import com.juandavyc.bakery.dto.productcategory.response.ProductCategoryResponseDTO;
import com.juandavyc.bakery.dto.productoccasion.ProductOccasionResponseDTO;
import com.juandavyc.bakery.entity.ProductCategoryEntity;
import com.juandavyc.bakery.entity.ProductOccasionEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String name,
        String slug,
        String cover,
        String description,
        BigInteger price,
//        Set<ProductCategoryResponseDTO> productCategories,
//        Set<ProductOccasionResponseDTO> productOccasions,
        LocalDateTime createdAt
) {
}
