package com.project.shop.Service;

import com.project.shop.Model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();
    Role findById(Long id);
    Role save(Role role);
    Role deleteById(Long id);
    Role update(Role role);
}