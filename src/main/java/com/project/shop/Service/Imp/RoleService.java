package com.project.shop.Service.Imp;

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
    private IRoleRepository iRoleRepository;

    @Override
    public List<Role> findAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> roleAux = iRoleRepository.findById(id);

        return roleAux.orElse(null);
    }

    @Override
    public Role save(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public Role deleteById(Long id) {
        Optional<Role> roleAux = iRoleRepository.findById(id);

        if (roleAux.isEmpty())
            return null;

        Role role = roleAux.get();
        role.setActive(false);
        iRoleRepository.save(role);

        return role;
    }

    @Override
    public Role update(Role role) {
        return iRoleRepository.save(role);
    }
}
