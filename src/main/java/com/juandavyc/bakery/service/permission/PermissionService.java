package com.juandavyc.bakery.service.permission;

import com.juandavyc.bakery.entity.PermissionEntity;
import com.juandavyc.bakery.entity.enums.PermissionEnum;

import java.util.List;

public interface PermissionService {

    PermissionEntity findById(Long id);
    PermissionEntity findByName(PermissionEnum permission);
    List<PermissionEntity> findAll();

}
