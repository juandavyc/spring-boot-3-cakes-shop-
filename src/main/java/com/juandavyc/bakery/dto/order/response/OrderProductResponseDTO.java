package com.juandavyc.bakery.dto.order.response;

import com.juandavyc.bakery.dto.product.response.ProductResponseDTO;
import com.juandavyc.bakery.entity.embeddable.OrderProductId;

import java.math.BigInteger;
import java.util.UUID;

public record OrderProductResponseDTO(
       OrderProductId orderProductId,
       BigInteger subtotal,
       Integer quantity
       //ProductResponseDTO productResponseDTO
) {
}
