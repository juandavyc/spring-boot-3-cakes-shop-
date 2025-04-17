package com.juandavyc.bakery.dto.productimage.response;

import java.time.LocalDateTime;

public record ProductImageResponseDTO(
        Integer id,
        String url,
        String altText,
        LocalDateTime createdAt
) {

}
