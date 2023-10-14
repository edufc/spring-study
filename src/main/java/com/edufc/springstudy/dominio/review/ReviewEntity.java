package com.edufc.springstudy.dominio.review;

import java.util.UUID;

import com.edufc.springstudy.dominio.book.BookEntity;
import com.edufc.springstudy.dominio.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "REVIEW")
@Getter
@Setter
@Builder
@ToString
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_BOOK", nullable = false)    
    private BookEntity id_book;

    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private UserEntity user;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
}
