package com.project.shop.Service.Imp;

import com.project.shop.Model.Permission;
import com.project.shop.Repository.IPermissionRepository;
import com.project.shop.Service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Permission> permission = permissionRepository.findById(id);

        if (permission.isEmpty())
            return;

        Permission permissionAux = permission.get();
        permissionAux.setActive(false);
    }

    @Override
    public Permission update(Permission permission) {
        permissionRepository.save(permission);

        return permission;
    }
}
