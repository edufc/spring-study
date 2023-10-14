package com.edufc.springstudy.dominio.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edufc.springstudy.dominio.BaseService;

import jakarta.transaction.Transactional;

@Service
public class BookService extends BaseService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        var entitys = this.bookRepository.findAll();

        return super.mapper.toBook(entitys);
    }

    @Transactional
    public Book addBook(final Book book) {
        var entity = this.bookRepository.save(super.mapper.toBook(book));

        return super.mapper.toBook(entity);
    }
}
