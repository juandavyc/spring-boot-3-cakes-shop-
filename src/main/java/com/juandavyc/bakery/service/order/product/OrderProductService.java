package com.juandavyc.bakery.service.order.product;


import com.juandavyc.bakery.dto.order.request.OrderProductRequestDTO;
import com.juandavyc.bakery.dto.order.response.OrderProductResponseDTO;
import com.juandavyc.bakery.entity.OrderProductEntity;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public interface OrderProductService {

    OrderProductResponseDTO create(UUID id, OrderProductRequestDTO orderProductRequestDTO);

    Set<OrderProductEntity> saveAll(UUID orderId, Set<OrderProductRequestDTO> products);
}
