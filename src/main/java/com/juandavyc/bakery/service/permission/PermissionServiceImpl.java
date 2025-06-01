package com.juandavyc.bakery.service.permission;

import com.juandavyc.bakery.entity.PermissionEntity;
import com.juandavyc.bakery.entity.enums.PermissionEnum;
import com.juandavyc.bakery.repository.PermissionRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {


    private final PermissionRepository permissionRepository;

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public PermissionEntity findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Permission not found")
                );
    }

    @Override
    public PermissionEntity findByName(PermissionEnum permission) {
        return permissionRepository.findByName(permission)
                .orElseThrow(
                        () -> new IllegalArgumentException("Permission with name:" + permission.name() + ", not found")
                );

    }

    @Override
    public List<PermissionEntity> findAll() {
        final var list = permissionRepository.findAll();

        if (list.isEmpty()) {
            throw new NoSuchElementException("Permissions not found");
        }
        return list;

    }
}
