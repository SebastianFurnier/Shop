package com.project.shop.Component;

import com.project.shop.Enum.DefaultPermissions;
import com.project.shop.Enum.DefaultRoles;
import com.project.shop.Service.IPermissionService;
import com.project.shop.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    private final List<DefaultRoles> defaultRoles = Arrays.stream(DefaultRoles.values()).toList();
    private final List<DefaultPermissions> defaultPermissions = Arrays.stream(DefaultPermissions.values()).toList();

    @Override
    public void run(String... args) throws Exception {

        for(DefaultPermissions permission : defaultPermissions) {
            if (!permissionService.existByName(permission.name()))
                permissionService.save(permission.name());
        }

        for (DefaultRoles role : defaultRoles) {
            if (!roleService.existByName(role.name()))
                roleService.save(role.name());
        }
    }
}
