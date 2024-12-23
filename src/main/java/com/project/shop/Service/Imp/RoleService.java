package com.project.shop.Service.Imp;

import com.project.shop.ExceptionHandler.ResourceNotFoundException;
import com.project.shop.Model.Role;
import com.project.shop.Repository.IRoleRepository;
import com.project.shop.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> roleAux = roleRepository.findById(id);

        if (roleAux.isEmpty())
            throw new ResourceNotFoundException("This id isn't exist.");

        return roleAux.get();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role deleteById(Long id) {
        Optional<Role> roleAux = roleRepository.findById(id);

        if (roleAux.isEmpty())
            throw new ResourceNotFoundException("This id isn't exist.");

        Role role = roleAux.get();
        role.setActive(false);
        roleRepository.save(role);

        return role;
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }
}