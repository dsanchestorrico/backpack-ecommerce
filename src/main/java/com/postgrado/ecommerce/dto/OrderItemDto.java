package com.postgrado.ecommerce.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDto {
    private Integer quantity;
    private UUID productId;
}
