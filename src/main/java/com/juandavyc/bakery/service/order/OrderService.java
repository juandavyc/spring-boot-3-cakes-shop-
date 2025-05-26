package com.juandavyc.bakery.service.order;


import com.juandavyc.bakery.dto.order.request.OrderRequestDTO;
import com.juandavyc.bakery.dto.order.response.OrderResponseDTO;

import java.util.UUID;

public interface OrderService {

    UUID create(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO getById(UUID orderId);
}
