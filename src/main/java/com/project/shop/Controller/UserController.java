package com.project.shop.Controller;

import com.project.shop.DTO.UserDTO;
import com.project.shop.Model.Role;
import com.project.shop.Model.UserSec;
import com.project.shop.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserSec userSec){
        UserDTO newUser = userService.createUser(userSec);
        return ResponseEntity.ok(newUser);
    }
}
