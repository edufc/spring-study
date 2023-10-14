package com.edufc.springstudy.dominio.book;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edufc.springstudy.util.ControllerUtil;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        var response = this.bookService.addBook(book);

        return ResponseEntity.created(ControllerUtil.getLocation(response.id())).build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        var response = this.bookService.getAllBooks();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable final UUID id) {
        var response = this.bookService.getBook(id);

        if (response.isPresent())
            return ResponseEntity.ok(response.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        this.bookService.updateBook(book);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable final UUID id) {
        var isDeleted = this.bookService.deleteBook(id);

        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
