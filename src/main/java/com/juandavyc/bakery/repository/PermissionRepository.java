package com.juandavyc.bakery.repository;

import com.juandavyc.bakery.entity.PermissionEntity;
import com.juandavyc.bakery.entity.enums.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    Optional<PermissionEntity> findByName(PermissionEnum permission);

}
