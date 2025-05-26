package com.juandavyc.bakery.dto.order.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Set;

@Data
@Builder

@AllArgsConstructor
public class OrderRequestDTO {

    @NotEmpty(message = "products cannot be empty")
    private Set<OrderProductRequestDTO> products;

    @NotNull(message = "total is required")
    private BigInteger total;

    @NotNull(message = "quantity is required")
    private Integer quantity;

}
