package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    @Override
    public Category getById(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Category", id));
        return category;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }
}
