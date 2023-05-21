package com.postgrado.ecommerce.dto;

import lombok.Data;

import java.util.List;
@Data
public class OrderDto {
    private String comment;
    private List<OrderItemDto>items;
}
