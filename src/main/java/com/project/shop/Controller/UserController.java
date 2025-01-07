package com.project.shop.Controller;

import com.project.shop.DTO.CreationalUserDTO;
import com.project.shop.DTO.UserDTO;
import com.project.shop.Model.UserSec;
import com.project.shop.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreationalUserDTO userSec) throws SQLIntegrityConstraintViolationException{

        UserDTO newUser = userService.createUser(userSec);

        URI location = URI.create(String.format("/users/get/%d", newUser.getId()));

        return ResponseEntity.created(location).body(newUser);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        UserDTO user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit")
    public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO user) {
        UserDTO editedUser = userService.editUser(user);

        return ResponseEntity.ok(editedUser);
    }

    @PutMapping("/editpassword")
    public ResponseEntity<UserDTO> editPassword(@RequestBody UserSec user){
        UserDTO editedUser = userService.editPassword(user);

        return ResponseEntity.ok(editedUser);
    }
}
