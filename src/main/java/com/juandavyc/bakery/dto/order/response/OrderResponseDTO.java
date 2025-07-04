package com.juandavyc.bakery.dto.order.response;

import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        BigInteger total,
        Integer quantity,
        Set<OrderProductResponseDTO> orderProducts
) {
}
