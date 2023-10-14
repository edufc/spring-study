package com.edufc.springstudy.dominio.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        var entitys = this.categoryRepository.findAll();

        return CategoryMapper.valueOf(entitys);
    }

    @Transactional
    public Category addCategory(final Category dto) {
        var entity = this.categoryRepository.save(CategoryMapper.valueOf(dto));

        return CategoryMapper.valueOf(entity);
    }
}