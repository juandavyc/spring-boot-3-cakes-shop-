package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface ProductRepository extends
        JpaRepository<ProductEntity, UUID>,
        JpaSpecificationExecutor<ProductEntity>
{


    ProductEntity findBySlug(String slug);

    @NonNull
    Page<ProductEntity> findAll(Specification<ProductEntity> specification, @NonNull Pageable pageable);
}
