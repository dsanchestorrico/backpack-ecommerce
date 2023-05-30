package com.postgrado.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private int stock;
    private boolean active;
    private UUID categoryId;
}
