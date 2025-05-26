package com.juandavyc.bakery.mapper.order.product;

import com.juandavyc.bakery.dto.order.request.OrderProductRequestDTO;
import com.juandavyc.bakery.dto.order.response.OrderProductResponseDTO;
import com.juandavyc.bakery.entity.OrderEntity;
import com.juandavyc.bakery.entity.OrderProductEntity;
import com.juandavyc.bakery.entity.ProductEntity;
import com.juandavyc.bakery.entity.embeddable.OrderProductId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    @Mapping(target = "orderProductId", expression = "java(orderProductIdBuild(orderId,productId))")
    @Mapping(target = "order", source = "orderId", qualifiedByName = "orderBuild")
    @Mapping(target = "product", source = "productId", qualifiedByName = "productBuild")
    @Mapping(target = "createdAt", ignore = true)
    OrderProductEntity orderProductRequestDTOToOrderProductEntity(UUID orderId,UUID productId,OrderProductRequestDTO orderProductRequestDTO);

    OrderProductResponseDTO orderProductEntityToOrderProductResponseDTO(OrderProductEntity orderProductEntity);

    @Named(value = "orderProductIdBuild")
    default OrderProductId orderProductIdBuild(UUID orderId,UUID productId){
        return OrderProductId.builder()
                .orderId(orderId)
                .productId(productId)
                .build();
    }

    @Named(value = "orderBuild")
    default OrderEntity orderBuild(UUID orderId){
        return OrderEntity.builder().id(orderId).build();
    }

    @Named(value = "productBuild")
    default ProductEntity productBuild(UUID productBuild){
        return ProductEntity.builder().id(productBuild).build();
    }


}
