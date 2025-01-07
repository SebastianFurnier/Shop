package com.project.shop.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreationUserDTO {
    private String name;
    private String username;
    private String lastname;
    private String address;
    @Email(message = "Invalid email format.")
    private String email;
    private String phoneNumber;
    private Date birthday;
    @NotBlank(message = "Password can't be empty.")
    private String password;
}
