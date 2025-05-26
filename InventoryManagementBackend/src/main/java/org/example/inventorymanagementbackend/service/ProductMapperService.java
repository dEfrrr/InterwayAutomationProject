package org.example.inventorymanagementbackend.service;

import org.example.inventorymanagementbackend.dto.ProductDTO;
import org.example.inventorymanagementbackend.model.Product;

public interface ProductMapperService {

    ProductDTO getAsProductDto(Product product);

}
