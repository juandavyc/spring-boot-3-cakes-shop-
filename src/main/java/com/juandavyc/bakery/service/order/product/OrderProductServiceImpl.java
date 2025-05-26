package com.juandavyc.bakery.service.order.product;

import com.juandavyc.bakery.dto.order.request.OrderProductRequestDTO;
import com.juandavyc.bakery.dto.order.response.OrderProductResponseDTO;
import com.juandavyc.bakery.entity.OrderProductEntity;
import com.juandavyc.bakery.mapper.order.product.OrderProductMapper;
import com.juandavyc.bakery.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {


    private final OrderProductRepository orderProductRepository;
    private final OrderProductMapper orderProductMapper;

    @Override
    public OrderProductResponseDTO create(UUID orderId, OrderProductRequestDTO orderProductRequestDTO) {

        final var productId = orderProductRequestDTO.getId();

        final var orderProductEntity = orderProductMapper
                .orderProductRequestDTOToOrderProductEntity(orderId, productId, orderProductRequestDTO);

        final var orderProductCreatedEntity = orderProductRepository.save(orderProductEntity);

        return orderProductMapper.orderProductEntityToOrderProductResponseDTO(orderProductCreatedEntity);
    }

    @Override
    public Set<OrderProductEntity> saveAll(UUID orderId, Set<OrderProductRequestDTO> products) {

        final var orderProductEntities = products.stream()
                .map(product ->
                        orderProductMapper.orderProductRequestDTOToOrderProductEntity(
                                orderId,
                                product.getId(),
                                product
                        )
                )
                .collect(Collectors.toSet());

        final var productEntitiesCreated = orderProductRepository.saveAll(orderProductEntities);

        return new HashSet<>(productEntitiesCreated);
    }
}
