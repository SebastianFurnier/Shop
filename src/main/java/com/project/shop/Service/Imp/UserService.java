package com.project.shop.Service.Imp;

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
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.searchUserById(userId);
        user.setActive(false);
    }

    @Override
    public User editPassword(String newPassword) {
        return null;
    }
}
