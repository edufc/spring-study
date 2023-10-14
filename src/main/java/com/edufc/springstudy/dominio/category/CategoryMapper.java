package com.edufc.springstudy.dominio.category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static Category valueOf(final CategoryEntity entity) {
        return new Category(entity.getId(), entity.getName());
    }

    public static List<Category> valueOf(final List<CategoryEntity> entity) {
        return entity.stream().map(CategoryMapper::valueOf).collect(Collectors.toList());
    }

    public static CategoryEntity valueOf(final Category entity) {
        return new CategoryEntity(entity.id(), entity.name());
    }
}