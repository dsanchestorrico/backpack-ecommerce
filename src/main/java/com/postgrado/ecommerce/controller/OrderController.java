package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    @PostMapping
    public ResponseEntity<String>create(@RequestBody OrderDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto>getById(@PathVariable UUID id){
        OrderDto orderDto = orderService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }
}
