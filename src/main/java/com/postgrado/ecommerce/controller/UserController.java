package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.UserDto;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.service.UserService;
import com.postgrado.ecommerce.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>>getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
}
