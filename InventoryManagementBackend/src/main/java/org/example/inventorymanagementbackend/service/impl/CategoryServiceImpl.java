package org.example.inventorymanagementbackend.service.impl;

import org.example.inventorymanagementbackend.dto.CategoryDTO;
import org.example.inventorymanagementbackend.model.enums.Category;
import org.example.inventorymanagementbackend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<CategoryDTO> getAllCategories() {
        return Arrays.stream(Category.values())
                .map(c -> new CategoryDTO(c.ordinal(), c.getDisplayName()))
                .collect(Collectors.toList());
    }
}
