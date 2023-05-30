package com.postgrado.ecommerce.repository;

import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
    Page<Product> findByCategoryId(UUID categoryId, Pageable pageable);
}
