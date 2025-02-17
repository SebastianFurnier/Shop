package com.project.shop.DTO;

import com.project.shop.Model.Role;
import com.project.shop.Model.UserSec;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Role role;

    public UserDTO (UserSec user) {
        this.id = user.getId();
        this.isActive = user.isEnabled();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.birthday = user.getBirthday();
        Set<Role> roles = user.getRolesList();
        this.role = roles.iterator().next();
    }
}
