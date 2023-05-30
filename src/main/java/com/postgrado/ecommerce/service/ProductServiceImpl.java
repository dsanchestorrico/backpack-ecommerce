package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.ProductDto;
import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.mapper.ProductMapper;
import com.postgrado.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryService categoryService;
    private ProductMapper productMapper;

    @Override
    public Product create(ProductDto dto) {

        Category category = categoryService.getById(dto.getCategoryId());
        Product product = productMapper.fromDto(dto);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product update(UUID id, ProductDto dto) {
        Category category = categoryService.getById(dto.getCategoryId());
        Product product = productMapper.fromDto(dto);
        product.setId(id);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product getById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product", id));
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsByCategoryId(UUID categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId,pageable);
    }

    @Override
    public Page<Product> getFilteredProducts(Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }
}
