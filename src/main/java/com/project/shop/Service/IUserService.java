package com.project.shop.Service;

import com.project.shop.DTO.CreationalUserDTO;
import com.project.shop.DTO.UserDTO;
import com.project.shop.Model.UserSec;

public interface IUserService {
    UserDTO createUser(CreationalUserDTO userSec);
    UserDTO editUser(UserDTO userDTO);
    void deleteUser(Long userId);
    UserDTO editPassword(UserSec user);
    public String encryptPassword(String password);
    UserDTO getUserById(long id);
    UserDTO getUserByUsername(String name);
}
