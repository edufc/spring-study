package com.edufc.springstudy.dominio.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edufc.springstudy.dominio.BaseService;

import jakarta.transaction.Transactional;

@Service
public class CategoryService extends BaseService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        var entitys = this.categoryRepository.findAll();

        return super.mapper.toCategory(entitys);
    }

    @Transactional
    public Category addCategory(final Category category) {
        var entity = this.categoryRepository.save(super.mapper.toCategory(category));

        return super.mapper.toCategory(entity);
    }
}