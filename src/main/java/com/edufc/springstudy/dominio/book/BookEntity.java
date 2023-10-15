package com.edufc.springstudy.dominio.book;

import java.util.List;
import java.util.UUID;

import com.edufc.springstudy.dominio.category.CategoryEntity;
import com.edufc.springstudy.dominio.review.ReviewEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookEntity {

    public BookEntity(final UUID id, final String name, final String description, final CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 255, nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "ID_CATEGORY", nullable = false)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BOOK")
    private List<ReviewEntity> reviews;
}
