package org.example.inventorymanagementbackend.service;

import org.example.inventorymanagementbackend.dto.CreateProductDTO;
import org.example.inventorymanagementbackend.model.Product;
import org.example.inventorymanagementbackend.model.enums.Category;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getAllProductsFiltered(String name, Double priceFrom, Double priceTo, Integer categoryId);

    Product getProductById(Long id);

    Product addProduct(CreateProductDTO createProductDTO);

    Product updateProduct(Long id, CreateProductDTO createProductDTO);

    Product deleteProduct(Long id);

}
