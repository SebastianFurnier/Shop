package com.project.shop.Controller;

import com.project.shop.ExceptionHandler.ResourceNotFoundException;
import com.project.shop.Model.Role;
import com.project.shop.Service.IRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@Valid @RequestBody String role){
        Role roleCreated = roleService.save(role);

        URI location = URI.create(String.format("/roles/get/%d", roleCreated.getId()));

        return ResponseEntity.created(location).body(roleCreated);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Role> delete(@PathVariable Long id){
        return ResponseEntity.ok(roleService.deleteById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Role> updateRole(@Valid @RequestBody Role role){
        return ResponseEntity.ok(roleService.update(role));
    }
}
