package com.project.shop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreationalUserDTO {
    private String name;
    private String username;
    private String lastname;
    private String address;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private String password;
}
