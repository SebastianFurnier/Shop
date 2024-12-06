package com.project.shop.Service.Imp;

import com.project.shop.Repository.IUserRepository;
import com.project.shop.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
}
