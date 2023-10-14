package com.edufc.springstudy.dominio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.edufc.springstudy.dominio.book.Book;
import com.edufc.springstudy.dominio.book.BookEntity;
import com.edufc.springstudy.dominio.category.Category;
import com.edufc.springstudy.dominio.category.CategoryEntity;

@Component
public class Mapper {

    public Category toCategory(final CategoryEntity entity) {
        return new Category(entity.getId(), entity.getName());
    }

    public List<Category> toCategory(final List<CategoryEntity> entity) {
        return entity.stream().map(o -> this.toCategory(o)).collect(Collectors.toList());
    }

    public CategoryEntity toCategory(final Category entity) {
        return new CategoryEntity(entity.name());
    }

    public Book toBook(final BookEntity entity) {
        return new Book(entity.getId(), entity.getName(), entity.getDescription(),
                this.toCategory(entity.getCategory()));
    }

    public List<Book> toBook(final List<BookEntity> entity) {
        return entity.stream().map(o -> this.toBook(o)).collect(Collectors.toList());
    }

    public BookEntity toBook(final Book entity) {
        return new BookEntity(entity.name(), entity.description(), this.toCategory(entity.category()));
    }
}