package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.Optional;


public interface UserRepository extends
        JpaRepository<UserEntity, Integer>,
        JpaSpecificationExecutor<UserEntity>

{

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @NonNull
    Page<UserEntity> findAll(Specification<UserEntity> specification, @NonNull Pageable pageable);

    Optional<UserEntity> findByUsername(String username);
}
