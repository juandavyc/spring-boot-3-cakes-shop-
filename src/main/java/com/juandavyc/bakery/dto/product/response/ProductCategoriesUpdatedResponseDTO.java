package com.juandavyc.bakery.dto.product.response;


import com.juandavyc.bakery.dto.productcategory.response.ProductCategoryResponseDTO;

import java.util.Set;
import java.util.UUID;

public record ProductCategoriesUpdatedResponseDTO(
        Set<ProductCategoryResponseDTO> productCategories
) {
}
