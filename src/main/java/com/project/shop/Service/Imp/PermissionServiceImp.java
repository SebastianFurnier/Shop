package com.project.shop.Service.Imp;

import com.project.shop.Model.Permission;
import com.project.shop.Repository.IPermissionRepository;
import com.project.shop.Service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImp implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findById(Long id) {
        Optional<Permission> permissionAux = permissionRepository.findById(id);

        return permissionAux.orElse(null);
    }

    @Override
    public Permission save(String name) {
        Permission permission = new Permission();
        permission.setPermissionName(name);
        permission.setActive(true);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission deleteById(Long id) {
        Optional<Permission> permission = permissionRepository.findById(id);

        if (permission.isEmpty())
            return null;

        Permission permissionAux = permission.get();
        permissionAux.setActive(false);

        permissionRepository.save(permissionAux);
        return permissionAux;
    }

    @Override
    public Permission update(Permission permission) {
        permissionRepository.save(permission);

        return permission;
    }
}
