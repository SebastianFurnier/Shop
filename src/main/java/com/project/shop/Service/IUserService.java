package com.project.shop.Service;

import com.project.shop.DTO.UserDTO;
import com.project.shop.Model.UserSec;

public interface IUserService {
    UserDTO createUser(UserSec userSec);
    UserSec editUser(UserSec userSec);
    void deleteUser(Long userId);
    UserSec editPassword(String newPassword);
    public String encriptPassword(String password);

}
