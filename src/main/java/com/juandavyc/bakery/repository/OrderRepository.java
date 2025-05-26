package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {


}
