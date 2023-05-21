package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    @PostMapping
    public ResponseEntity<String>create(@RequestBody OrderDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(dto));
    }
}
