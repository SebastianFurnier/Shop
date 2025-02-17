package com.project.shop.Service;

import com.project.shop.DTO.CreationUserDTO;
import com.project.shop.DTO.UserDTO;
import com.project.shop.Model.Role;
import com.project.shop.Model.UserSec;

public interface IUserService {
    UserDTO createUser(CreationUserDTO userSec);
    UserDTO editUser(UserDTO userDTO);
    void deleteUser(Long userId);
    UserDTO editPassword(UserSec user);
    public String encryptPassword(String password);
    UserDTO getUserById(long id);
    UserDTO getUserByUsername(String name);
}
