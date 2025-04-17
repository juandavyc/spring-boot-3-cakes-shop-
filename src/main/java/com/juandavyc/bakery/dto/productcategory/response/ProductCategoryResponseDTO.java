package com.juandavyc.bakery.dto.productcategory.response;

import com.juandavyc.bakery.dto.category.response.CategoryResponseDTO;
import com.juandavyc.bakery.entity.embeddable.ProductCategoryId;



public record ProductCategoryResponseDTO(
        ProductCategoryId productCategoryId,
        CategoryResponseDTO category
) {
}
