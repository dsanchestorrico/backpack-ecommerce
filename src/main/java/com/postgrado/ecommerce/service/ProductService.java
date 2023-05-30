package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    Product create(ProductDto dto);
    Product update(UUID id, ProductDto dto);
    Product getById(UUID id);
    Page<Product> getProducts(Pageable pageable);
    Page<Product> getProductsByCategoryId(UUID categoryId, Pageable pageable);
    Page<Product> getFilteredProducts(Double minPrice, Double maxPrice, Pageable pageable);
}
