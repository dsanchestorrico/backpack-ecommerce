package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.entity.Role;
import com.postgrado.ecommerce.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;
    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(role));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getByName(@PathVariable String name) {
        Role roleFound = roleService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(roleFound);
    }

    @GetMapping
    public ResponseEntity<List<Role>>getAll(){
        List<Role> roles = roleService.getAll();
        return ResponseEntity.ok(roles);
    }

}

