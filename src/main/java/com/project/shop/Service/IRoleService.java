package com.project.shop.Service;

import com.project.shop.Model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();
    Role findByName(String name);
    Role findById(Long id);
    Role save(String role);
    Role deleteById(Long id);
    Role update(Role role);
    boolean existByName(String name);
}