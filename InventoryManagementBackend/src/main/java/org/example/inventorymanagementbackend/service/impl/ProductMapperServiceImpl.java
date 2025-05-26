package org.example.inventorymanagementbackend.service.impl;

import org.example.inventorymanagementbackend.dto.CategoryDTO;
import org.example.inventorymanagementbackend.dto.ProductDTO;
import org.example.inventorymanagementbackend.model.Product;
import org.example.inventorymanagementbackend.service.ProductMapperService;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperServiceImpl implements ProductMapperService {
    @Override
    public ProductDTO getAsProductDto(Product product) {
        ProductDTO resultProductDto = new ProductDTO();

        resultProductDto.setId(product.getId());
        resultProductDto.setName(product.getName());
        resultProductDto.setDescription(product.getDescription());
        resultProductDto.setPrice(product.getPrice());
        resultProductDto.setQuantityInStock(product.getQuantityInStock());
        resultProductDto.setCategory(new CategoryDTO(product.getCategory().ordinal(), product.getCategory().getDisplayName()));

        return resultProductDto;
    }
}
