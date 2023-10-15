package com.edufc.springstudy.category;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.edufc.springstudy.dominio.category.Category;
import com.edufc.springstudy.dominio.category.CategoryController;
import com.edufc.springstudy.dominio.category.CategoryService;

@WebMvcTest(controllers = CategoryController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CategoryControllerTest {

    @MockBean
    private CategoryService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        var list = Arrays.asList(new Category((short) 1, "terror"), new Category((short) 2, "drama"));

        when(service.getAllCategories()).thenReturn(list);

        this.mockMvc.perform(get("/categories")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("terror")));
    }
}
