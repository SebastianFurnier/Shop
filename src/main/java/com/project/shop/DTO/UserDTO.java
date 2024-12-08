package com.project.shop.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private long id;
    private boolean isActive;
    private String name;
    private String lastname;
    private String address;
    private String email;
    private String phoneNumber;
    private Date birthday;
}
