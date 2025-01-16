package com.project.shop.Model;

import com.project.shop.DTO.CreationUserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.RoleList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class UserSec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean enabled;
    @Column(unique = true)
    @NotBlank(message = "Username can't be blank or empty.")
    private String username;
    private String name;
    private String lastname;
    private String password;
    private String address;
    @Column(unique = true)
    @NotBlank(message = "Email can't be empty.")
    private String email;
    private String phoneNumber;
    private Date birthday;

    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialNotExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> rolesList = new HashSet<>();

    public UserSec (CreationUserDTO user){
        this.enabled = true;
        this.username = user.getUsername();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.birthday = user.getBirthday();

        this.accountNotExpired = true;
        this.accountNotLocked = true;
        this.credentialNotExpired = true;
    }

    public Set<Role> addRole(Role role) {
        this.rolesList.add(role);
        return this.rolesList;
    }
}
