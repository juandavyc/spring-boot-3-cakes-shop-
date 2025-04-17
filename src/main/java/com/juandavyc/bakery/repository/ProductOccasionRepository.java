package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.ProductOccasionEntity;
import com.juandavyc.bakery.entity.embeddable.ProductOccasionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOccasionRepository
        extends JpaRepository<ProductOccasionEntity, ProductOccasionId> {
}
