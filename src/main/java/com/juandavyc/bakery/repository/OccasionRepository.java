package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.OccasionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Set;

public interface OccasionRepository extends
        JpaRepository<OccasionEntity, Integer> {

    OccasionEntity findBySlug(String slug);

    Long countByIdIn(Set<Integer> ids);
}
