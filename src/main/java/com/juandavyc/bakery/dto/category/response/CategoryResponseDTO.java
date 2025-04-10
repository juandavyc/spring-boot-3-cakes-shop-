package com.juandavyc.bakery.dto.category.response;


import java.time.LocalDateTime;

public record CategoryResponseDTO(
        Integer id,
        String name,
        String slug,
        LocalDateTime createdAt
) {
}
