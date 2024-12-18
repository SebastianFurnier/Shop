package com.project.shop.Service;

import com.project.shop.Model.Permission;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    List<Permission> findAll();
    Optional findById(Long id);
    Permission save(Permission permission);
    void deleteById(Long id);
    Permission update(Permission permission);
}
