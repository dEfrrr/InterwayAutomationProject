package org.example.inventorymanagementbackend.service.impl;

import org.example.inventorymanagementbackend.dto.CategoryDTO;
import org.example.inventorymanagementbackend.dto.CreateProductDTO;
import org.example.inventorymanagementbackend.dto.ProductDTO;
import org.example.inventorymanagementbackend.exception.ProductNotFoundException;
import org.example.inventorymanagementbackend.model.Product;
import org.example.inventorymanagementbackend.model.enums.Category;
import org.example.inventorymanagementbackend.repository.ProductRepository;
import org.example.inventorymanagementbackend.service.CategoryService;
import org.example.inventorymanagementbackend.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsFiltered(String name, Double priceFrom, Double priceTo, Integer categoryId) {
        List<CategoryDTO> categoryList = categoryService.getAllCategories()
                .stream().filter(c -> c.getId() == categoryId)
                .toList();

        if (categoryList.isEmpty()) {
            return productRepository.getAllProductsFiltered(name, priceFrom, priceTo, null);
        } else {
            return productRepository.getAllProductsFiltered(name, priceFrom, priceTo, Category.fromDisplayName(categoryList.get(0).getCategoryName()));
        }

    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " was not found."));
    }

    @Override
    public Product addProduct(CreateProductDTO createProductDTO) {
        Product product = new Product();
        
        product.setName(createProductDTO.getName());
        product.setDescription(createProductDTO.getDescription());
        product.setPrice(createProductDTO.getPrice());
        product.setQuantityInStock(createProductDTO.getQuantityInStock());
        String categoryName = categoryService.getAllCategories()
                .stream().filter(c -> c.getId() == createProductDTO.getCategory())
                .toList().get(0).getCategoryName();
        product.setCategory(Category.fromDisplayName(categoryName));

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, CreateProductDTO createProductDTO) {
        Product product = getProductById(id);

        product.setName(createProductDTO.getName());
        product.setDescription(createProductDTO.getDescription());
        product.setPrice(createProductDTO.getPrice());
        product.setQuantityInStock(createProductDTO.getQuantityInStock());
        String categoryName = categoryService.getAllCategories()
                .stream().filter(c -> c.getId() == createProductDTO.getCategory())
                .toList().get(0).getCategoryName();
        product.setCategory(Category.fromDisplayName(categoryName));

        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = getProductById(id);

        productRepository.delete(product);

        return product;
    }


}
