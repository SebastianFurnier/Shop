package com.project.shop.Service.Imp;

import com.project.shop.DTO.UserDTO;
import com.project.shop.Model.Role;
import com.project.shop.Model.UserSec;
import com.project.shop.Repository.IUserRepository;
import com.project.shop.Service.IRoleService;
import com.project.shop.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleService roleService;

    @Override
    public UserDTO createUser(UserSec userSec) {

        Set<Role> roleList = new HashSet<>();

        Role readRole;

        userSec.setPassword(encriptPassword(userSec.getPassword()));

        for (Role role : userSec.getRolesList()){
            readRole = roleService.findById(role.getId());
            if (readRole != null) {
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty())
            return null;

        userSec.setRolesList(roleList);

        UserSec newUserSec = userRepository.save(userSec);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(newUserSec.getId());
        userDTO.setActive(newUserSec.isEnabled());
        userDTO.setName(newUserSec.getName());
        userDTO.setLastname(newUserSec.getLastname());
        userDTO.setAddress(newUserSec.getAddress());
        userDTO.setEmail(newUserSec.getEmail());
        userDTO.setPhoneNumber(newUserSec.getPhoneNumber());
        userDTO.setBirthday(newUserSec.getBirthday());

        return userDTO;
    }

    @Override
    public UserSec editUser(UserSec userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public void deleteUser(Long userId) {
        UserSec userSec = userRepository.searchUserById(userId);
        userSec.setEnabled(false);
    }

    @Override
    public UserSec editPassword(String newPassword) {
        return null;
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
