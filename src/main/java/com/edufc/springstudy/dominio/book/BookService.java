package com.edufc.springstudy.dominio.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        var entitys = this.bookRepository.findAll();

        return BookMapper.valueOf(entitys);
    }

    public Optional<Book> getBook(final UUID id) {
        return this.bookRepository.findById(id).map(BookMapper::valueOf);
    }

    @Transactional
    public Book addBook(final Book dto) {
        var entity = this.bookRepository.save(BookMapper.valueOf(dto));

        return BookMapper.valueOf(entity);
    }

    @Transactional
    public void updateBook(final Book dto) {
        var entity = this.bookRepository.findById(dto.id());

        if (entity.isPresent()) {
            BookMapper.merge(entity.get(), dto);
            this.bookRepository.save(entity.get());
        }
    }

    @Transactional
    public boolean deleteBook(final UUID id) {
        var entity = this.bookRepository.findById(id);

        if (entity.isPresent()) {
            this.bookRepository.delete(entity.get());
            return true;
        } else {
            return false;
        }
    }
}