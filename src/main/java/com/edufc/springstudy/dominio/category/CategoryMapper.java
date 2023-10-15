package com.edufc.springstudy.dominio.category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static Category valueOf(final CategoryEntity entity) {
        if (null != entity)
            return new Category(entity.getId(), entity.getName());
        else
            return null;
    }

    public static List<Category> valueOf(final List<CategoryEntity> entity) {
        return entity.stream().map(CategoryMapper::valueOf).collect(Collectors.toList());
    }

    public static CategoryEntity valueOf(final Category dto) {
        if (null != dto)
            return new CategoryEntity(dto.id(), dto.name());
        else
            return null;
    }

    public static void merge(CategoryEntity entity, final Category dto) {
        entity.setName(dto.name());
    }
}