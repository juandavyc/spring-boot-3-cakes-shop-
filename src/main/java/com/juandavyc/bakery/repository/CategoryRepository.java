package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends
        JpaRepository<CategoryEntity, Integer> {

    CategoryEntity findBySlug(String slug);

    Long countByIdIn(Set<Integer> ids);

}
