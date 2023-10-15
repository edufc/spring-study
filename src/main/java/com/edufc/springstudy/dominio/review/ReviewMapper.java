package com.edufc.springstudy.dominio.review;

import java.util.List;
import java.util.stream.Collectors;

import com.edufc.springstudy.dominio.book.BookMapper;
import com.edufc.springstudy.dominio.user.UserMapper;

public class ReviewMapper {

    public static Review valueOf(final ReviewEntity entity) {
        return new Review(entity.getId(), entity.getDescription(), BookMapper.valueOf(entity.getBook()), UserMapper.valueOf(entity.getUser()));
    }

    public static List<Review> valueOf(final List<ReviewEntity> entity) {
        return entity.stream().map(ReviewMapper::valueOf).collect(Collectors.toList());
    }

    public static ReviewEntity valueOf(final Review dto) {
        return new ReviewEntity(dto.id(), dto.description(), BookMapper.valueOf(dto.book()), UserMapper.valueOf(dto.user()));
    }

    public static void merge(ReviewEntity entity, final Review dto) {
        entity.setDescription(dto.description());
    }
}
