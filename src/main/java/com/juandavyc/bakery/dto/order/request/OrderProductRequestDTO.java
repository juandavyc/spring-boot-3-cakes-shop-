package com.juandavyc.bakery.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class OrderProductRequest {

    private UUID id;

    private BigInteger subtotal;

    private Integer quantity;

}
