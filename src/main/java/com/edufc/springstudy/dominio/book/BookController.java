package com.edufc.springstudy.dominio.book;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
