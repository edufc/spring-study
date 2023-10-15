package com.edufc.springstudy.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.edufc.springstudy.dominio.category.Category;
import com.edufc.springstudy.dominio.category.CategoryEntity;
import com.edufc.springstudy.dominio.category.CategoryRepository;
import com.edufc.springstudy.dominio.category.CategoryService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class CategoryServiceTest {
    @Autowired
    CategoryService service;

    @MockBean
    private CategoryRepository repository;

    @Captor
    private ArgumentCaptor<CategoryEntity> categoryArgument;

    @Test
    @DisplayName("Is even number")
    public void isEvenNumber() {
        // CategoryService service = new CategoryService();
        assertThat(service.isEvenNumber(2)).isTrue();
    }

    @Test
    @DisplayName("Is error divide by zero")
    public void isNumberOne() {
        // CategoryService service = new CategoryService();

        assertThatThrownBy(() -> service.isEvenNumber(0)).isInstanceOf(ArithmeticException.class);
    }

    @Test
    @DisplayName("Should find by ID")
    public void shouldFindById() {
        short id = 20;
        String name = "terror";

        var entity = Optional.of(new CategoryEntity(id, name));

        Mockito.when(repository.findById(id)).thenReturn(entity);

        var dto = this.service.getCategory(id);

        assertThat(dto.isPresent()).isTrue();
        assertThat(dto.get().id()).isEqualTo(id);
        assertThat(dto.get().name()).isEqualTo(name);
    }

    @Test
    @DisplayName("Should Add")
    public void shouldAdd() {
        short id = 21;
        String name = "terror";

        var dto = new Category(id, name);

        this.service.addCategory(dto);

        Mockito.verify(repository, Mockito.times(1)).save(categoryArgument.capture());    
        
        assertThat(categoryArgument.getValue().getId()).isEqualTo(id);
        assertThat(categoryArgument.getValue().getName()).isEqualTo(name);
    }
}
