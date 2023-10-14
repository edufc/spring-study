package com.edufc.springstudy.dominio.review;

import java.util.UUID;

import com.edufc.springstudy.dominio.book.Book;
import com.edufc.springstudy.dominio.user.User;

public record Review(UUID id, String description, Book book, User user) {

}
