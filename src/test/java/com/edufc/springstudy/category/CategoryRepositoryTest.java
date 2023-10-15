package com.edufc.springstudy.category;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.edufc.springstudy.dominio.category.CategoryEntity;
import com.edufc.springstudy.dominio.category.CategoryRepository;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    private CategoryEntity entity;

    @BeforeAll
    public void setup() {
        this.entity = new CategoryEntity((short) 0, "terror");
    }

    @Test
    @DisplayName("Should Add")
    public void shouldAdd() {
        var savedEntity = this.repository.save(entity);

        assertThat(savedEntity).usingRecursiveComparison().ignoringFields("id").isEqualTo(entity);
    }

    @Test
    @DisplayName("Should find by ID")
    @Sql({ "/script.sql" })
    public void shouldFindById() {
        short id = 2;

        var entity = this.repository.findById(id);

        assertThat(entity.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should find All")
    @Sql({ "/script.sql" })
    public void shouldFindAll() {
        var entities = this.repository.findAll();

        assertThat(entities.size() > 1).isTrue();
    }
}