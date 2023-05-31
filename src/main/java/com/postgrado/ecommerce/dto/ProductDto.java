package com.postgrado.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;
@Data
@NoArgsConstructor
public class ProductDto {
    @NotBlank(message = "{product.name.not-blank}")
    @Size(min = 3, max = 70, message = "{product.name.size}")
    private String name;
    @NotBlank(message = "{product.description.not-blank}")
    private String description;
    @URL
    private String imageUrl;
    @NotNull
    @DecimalMin(value = "0.0", message = "{product.price.min}", inclusive = false)
    private double price;
    @NotNull
    @Min(value = 0, message = "{product.stock.min}")
    private int stock;
    @NotNull
    private boolean active;
    @NotNull
    private UUID categoryId;
}
