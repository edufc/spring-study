package com.edufc.springstudy.dominio.book;

import java.util.UUID;

import com.edufc.springstudy.dominio.category.Category;

public record Book(UUID id, String name, String description, Category category) {
}
