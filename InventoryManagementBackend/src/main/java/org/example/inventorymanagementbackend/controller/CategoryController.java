package org.example.inventorymanagementbackend.controller;

import org.example.inventorymanagementbackend.dto.CategoryDTO;
import org.example.inventorymanagementbackend.model.enums.Category;
import org.example.inventorymanagementbackend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<CategoryDTO> categories = categoryService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
