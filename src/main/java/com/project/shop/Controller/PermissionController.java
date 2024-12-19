package com.project.shop.Controller;

import com.project.shop.Model.Permission;
import com.project.shop.Service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @PostMapping("/create/{name}")
    public ResponseEntity<Permission> createPermission(@PathVariable String name){
        return ResponseEntity.ok(permissionService.save(name));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Permission>> getAllPermissions(){
        return ResponseEntity.ok(permissionService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Permission> getById(@PathVariable Long id){
        return ResponseEntity.ok(permissionService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Permission> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(permissionService.deleteById(id));
    }

    @PutMapping("/edit")
    public ResponseEntity<Permission> editPermission(@RequestBody Permission permission){
        return ResponseEntity.ok(permissionService.update(permission));
    }
}
