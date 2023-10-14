package com.edufc.springstudy.dominio.book;

import java.util.List;
import java.util.stream.Collectors;

import com.edufc.springstudy.dominio.category.CategoryMapper;

public class BookMapper {

    public static Book valueOf(final BookEntity entity) {
        return new Book(entity.getId(), entity.getName(), entity.getDescription(),
                CategoryMapper.valueOf(entity.getCategory()));
    }

    public static List<Book> valueOf(final List<BookEntity> entity) {
        return entity.stream().map(BookMapper::valueOf).collect(Collectors.toList());
    }

    public static BookEntity valueOf(final Book entity) {
        return new BookEntity(entity.name(), entity.description(), CategoryMapper.valueOf(entity.category()));
    }

    public static void merge(BookEntity entity, final Book dto) {
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setCategory(CategoryMapper.valueOf(dto.category()));
    }
}