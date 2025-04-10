package com.juandavyc.bakery.dto.product.response;

import java.math.BigInteger;
import java.util.UUID;

public record ProductCreatedResponseDTO(
        UUID id,
        String name,
        String slug,
        BigInteger price
) {
}
