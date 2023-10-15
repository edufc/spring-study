package com.edufc.springstudy.dominio.review;

import java.util.UUID;

import com.edufc.springstudy.dominio.book.BookEntity;
import com.edufc.springstudy.dominio.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REVIEW")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "ID_BOOK", nullable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private UserEntity user;
}