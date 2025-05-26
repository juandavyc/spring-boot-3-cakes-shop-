package com.juandavyc.bakery.dto.order.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class OrderProductRequestDTO {

    @NotNull(message = "id is required")
    private UUID id;

    @NotNull(message = "subtotal is required")
    private BigInteger subtotal;

    @NotNull(message = "quantity is required")
    private Integer quantity;

}
