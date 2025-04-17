package com.juandavyc.bakery.dto.occasion.response;

import java.time.LocalDateTime;

public record OccasionResponseDTO(
        Integer id,
        String name,
        String slug
) {
}
