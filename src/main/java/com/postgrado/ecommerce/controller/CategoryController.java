package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/{id}")
    public ResponseEntity<Category>getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Category>>getAll(){
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping
    public ResponseEntity<Category>create(@RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(category));
    }

}
