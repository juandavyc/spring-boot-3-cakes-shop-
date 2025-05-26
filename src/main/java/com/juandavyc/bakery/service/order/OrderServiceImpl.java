package com.juandavyc.bakery.service.order;

import com.juandavyc.bakery.dto.order.request.OrderRequestDTO;
import com.juandavyc.bakery.dto.order.response.OrderResponseDTO;
import com.juandavyc.bakery.entity.OrderProductEntity;
import com.juandavyc.bakery.mapper.order.OrderMapper;
import com.juandavyc.bakery.repository.OrderRepository;
import com.juandavyc.bakery.service.order.product.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final OrderProductService orderProductService;


    @Override
    public OrderResponseDTO getById(UUID orderId) {

        final var orderEntity = orderRepository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("Order Id " + orderId + " not found"));

        return orderMapper.orderEntityToOrderResponseDTO(orderEntity);

    }

    @Override
    public UUID create(OrderRequestDTO orderRequestDTO) {

        final var orderEntity = orderMapper.orderRequestDTOToOrderEntity(orderRequestDTO);

        final var orderEntityCreated = orderRepository.save(orderEntity);

        final var orderId = orderEntityCreated.getId();


        Set<OrderProductEntity> orderProductsEntities = orderProductService
                .saveAll(orderId, orderRequestDTO.getProducts());

        orderEntityCreated.setOrderProducts(orderProductsEntities);

//        return orderMapper.orderEntityToOrderResponseDTO(orderEntityCreated);

        return orderId;
    }


}
