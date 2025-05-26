package com.juandavyc.bakery.dto.productoccasion;

import com.juandavyc.bakery.dto.category.response.CategoryResponseDTO;
import com.juandavyc.bakery.dto.occasion.response.OccasionResponseDTO;
import com.juandavyc.bakery.entity.embeddable.ProductCategoryId;
import com.juandavyc.bakery.entity.embeddable.ProductOccasionId;

import java.time.LocalDateTime;

public record ProductOccasionResponseDTO(
        ProductOccasionId productOccasionId,
        OccasionResponseDTO occasion
) {

}
