package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository
        extends JpaRepository<ProductImageEntity, Integer> {


}
