package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product>create(@Valid @RequestBody ProductDto dto){
        Product productSaved = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product>update(@PathVariable UUID id, @RequestBody ProductDto dto){
        Product productUpdated = productService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product>getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id));
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getProducts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productPage);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getFilteredProducts(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        if (minPrice == null) {
            minPrice = Double.MIN_VALUE;
        }
        if (maxPrice == null) {
            maxPrice = Double.MAX_VALUE;
        }
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productService.getFilteredProducts(minPrice, maxPrice, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productPage);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<Page<Product>> getProductsByCategoryId(@PathVariable("id") UUID categoryId, @RequestParam int page , @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getProductsByCategoryId(categoryId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productPage);
    }
}
