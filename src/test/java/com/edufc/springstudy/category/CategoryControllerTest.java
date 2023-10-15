package com.edufc.springstudy.category;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.edufc.springstudy.dominio.category.Category;
import com.edufc.springstudy.dominio.category.CategoryController;
import com.edufc.springstudy.dominio.category.CategoryService;

@WebMvcTest(controllers = CategoryController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class CategoryControllerTest {

    @MockBean
    private CategoryService service;

    @Autowired
    private MockMvc mockMvc;

    private List<Category> list;

    @BeforeEach
    public void setup() {
        list = Arrays.asList(new Category((short) 1, "terror"), new Category((short) 2, "drama"));
    }

    @Test
    public void shouldFindAll() throws Exception {
        when(service.getAllCategories()).thenReturn(list);

        this.mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("terror")));
    }

    @Test
    public void shouldFindById() throws Exception {
        short id = 2;

        when(service.getCategory(id)).thenReturn(Optional.of(list.get(1)));

        this.mockMvc.perform(get("/categories/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is((int) id)))
                .andExpect(jsonPath("$.name", Matchers.is("drama")));
    }

    @Test
    public void shouldThrow404() throws Exception {
        short id = 2;

        this.mockMvc.perform(get("/categories/{id}", id))                
                .andExpect(status().isNotFound());
    }

        @Test
    public void shouldAdd() throws Exception {
        var dto = new Category((short) 0, "comedy");

        when(service.addCategory(dto)).thenReturn(dto);

        this.mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON))                
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }
}
