package com.project.shop.Service.Imp;

import com.project.shop.DTO.UserDTO;
import com.project.shop.Model.User;
import com.project.shop.Repository.IUserRepository;
import com.project.shop.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDTO createUser(User user) {
        User newUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(newUser.getId());
        userDTO.setActive(newUser.isEnabled());
        userDTO.setName(newUser.getName());
        userDTO.setLastname(newUser.getLastname());
        userDTO.setAddress(newUser.getAddress());
        userDTO.setEmail(newUser.getEmail());
        userDTO.setPhoneNumber(newUser.getPhoneNumber());
        userDTO.setBirthday(newUser.getBirthday());

        return userDTO;
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.searchUserById(userId);
        user.setEnabled(false);
    }

    @Override
    public User editPassword(String newPassword) {
        return null;
    }
}
