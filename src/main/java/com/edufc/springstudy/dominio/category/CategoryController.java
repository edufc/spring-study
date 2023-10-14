package com.edufc.springstudy.dominio.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edufc.springstudy.util.ControllerUtil;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        var response = this.categoryService.addCategory(category);

        return ResponseEntity.created(ControllerUtil.getLocation(response.id())).build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        var response = this.categoryService.getAllCategories();

        return ResponseEntity.ok(response);
    }
}
