package com.project.shop.Service;

import com.project.shop.DTO.UserDTO;
import com.project.shop.Model.User;

public interface IUserService {
    UserDTO createUser(User user);
    User editUser(User user);
    void deleteUser(Long userId);
    User editPassword(String newPassword);

}
