package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.ProductCategoryEntity;
import com.juandavyc.bakery.entity.embeddable.ProductCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ProductCategoryRepository
        extends JpaRepository<ProductCategoryEntity, ProductCategoryId> {


    @Modifying
    @Transactional
    @Query("DELETE FROM ProductCategoryEntity pc WHERE pc.productCategoryId.productId = :productId")
    void deleteByProductId(@Param("productId") UUID productId);
}
