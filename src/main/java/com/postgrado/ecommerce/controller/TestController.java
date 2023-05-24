package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.OrderItemDto;
import com.postgrado.ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/sql")
public class TestController {
    private OrderRepository orderRepository;

    @GetMapping("/{orderid}")
    public String getTotalPrice(@PathVariable("orderid")UUID id){
        Double totalPriceSQL = orderRepository.getTotalPriceByOrderById(id.toString());
        Double totalPriceJPQL = orderRepository.getTotalPrice(id);
        return "Total price SQL: " + totalPriceSQL + " Total price JPQL: " + totalPriceJPQL;
    }

    @GetMapping("items/{id}")
    public List<OrderItemDto> getListItems(@PathVariable("id")UUID id){
           return orderRepository.getItemsWithTotalPrice(id);
    }
}
