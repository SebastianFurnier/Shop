package com.project.shop.Service.Imp;

import com.project.shop.Model.UserSec;
import com.project.shop.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServices implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSec userSec = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " doesn't exist"));

        List<GrantedAuthority> authorityList = new ArrayList<>();
        userSec.getRolesList()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));

        userSec.getRolesList().stream()
                .flatMap(role -> role.getPermissionsList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        return new User(userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isCredentialNotExpired(),
                userSec.isAccountNotLocked(),
                authorityList);
    }
}
