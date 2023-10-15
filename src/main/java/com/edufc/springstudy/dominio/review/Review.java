package com.edufc.springstudy.dominio.review;

import com.edufc.springstudy.dominio.book.Book;
import com.edufc.springstudy.dominio.user.User;

public record Review(int id, String description, Book book, User user) {

}
