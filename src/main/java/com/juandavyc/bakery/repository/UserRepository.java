package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.UserEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
