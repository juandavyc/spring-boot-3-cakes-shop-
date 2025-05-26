package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.OrderProductEntity;
import com.juandavyc.bakery.entity.embeddable.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, OrderProductId> {

}
