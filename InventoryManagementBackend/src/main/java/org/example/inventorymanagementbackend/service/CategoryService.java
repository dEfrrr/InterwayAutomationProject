package org.example.inventorymanagementbackend.service;

import org.example.inventorymanagementbackend.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
}
