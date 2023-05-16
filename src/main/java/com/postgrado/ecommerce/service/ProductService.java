package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;

import java.util.UUID;

public interface ProductService {
    Product create(ProductDto dto);
    Product getById(UUID id);
}
