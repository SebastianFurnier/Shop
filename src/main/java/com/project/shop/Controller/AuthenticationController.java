package com.project.shop.Controller;

import com.project.shop.DTO.AuthLoginRequestDTO;
import com.project.shop.DTO.AuthResponseDTO;
import com.project.shop.Service.Imp.UserDetailsServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserDetailsServices userDetailsServices;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(userDetailsServices.loginUser(userRequest), HttpStatus.OK);
    }
}
