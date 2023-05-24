package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.entity.Order;

import java.util.UUID;

public interface OrderService {
    String create(OrderDto order);
    OrderDto getById(UUID id);
}
