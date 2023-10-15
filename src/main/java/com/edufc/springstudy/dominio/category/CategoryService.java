package com.edufc.springstudy.dominio.category;

import java.util.List;
import java.util.Optional;

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

    public Optional<Category> getCategory(final short id) {
        return this.categoryRepository.findById(id).map(CategoryMapper::valueOf);
    }

    @Transactional
    public Category addCategory(final Category dto) {
        var entity = this.categoryRepository.save(CategoryMapper.valueOf(dto));

        return CategoryMapper.valueOf(entity);
    }

    @Transactional
    public void updateCategory(final Category dto) {
        var entity = this.categoryRepository.findById(dto.id());

        if (entity.isPresent()) {
            CategoryMapper.merge(entity.get(), dto);
            this.categoryRepository.save(entity.get());
        }
    }

    @Transactional
    public boolean deleteCategory(final short id) {
        var entity = this.categoryRepository.findById(id);

        if (entity.isPresent()) {
            this.categoryRepository.delete(entity.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean isEvenNumber(int a) {
        return 10 % a == 0 ? true : false;
    }
}