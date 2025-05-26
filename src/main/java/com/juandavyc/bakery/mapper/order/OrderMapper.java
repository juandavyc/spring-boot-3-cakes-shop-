package com.juandavyc.bakery.mapper.order;

import com.juandavyc.bakery.dto.order.request.OrderRequestDTO;
import com.juandavyc.bakery.dto.order.response.OrderResponseDTO;
import com.juandavyc.bakery.entity.OrderEntity;
import com.juandavyc.bakery.mapper.order.product.OrderProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderProductMapper.class)
public interface OrderMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrderEntity orderRequestDTOToOrderEntity(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO orderEntityToOrderResponseDTO(OrderEntity orderEntity);

}
