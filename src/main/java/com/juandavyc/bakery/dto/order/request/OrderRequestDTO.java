package com.juandavyc.bakery.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Set;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Set<OrderProductRequest> products;

    private BigInteger total;

    private Integer quantity;

}
