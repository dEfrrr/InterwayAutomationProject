package org.example.inventorymanagementbackend.controller;


import org.example.inventorymanagementbackend.dto.CreateProductDTO;
import org.example.inventorymanagementbackend.dto.ProductDTO;
import org.example.inventorymanagementbackend.model.enums.Category;
import org.example.inventorymanagementbackend.service.ProductMapperService;
import org.example.inventorymanagementbackend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapperService productMapperService;

    public ProductController(ProductService productService, ProductMapperService productMapperService) {
        this.productService = productService;
        this.productMapperService = productMapperService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) Double priceFrom,
                                                           @RequestParam(required = false) Double priceTo,
                                                           @RequestParam(required = false) Integer categoryId) {

        List<ProductDTO> products;
        if (name == null && priceFrom == null && priceTo == null && categoryId == null) {
            products = productService.getAllProducts()
                    .stream()
                    .map(productMapperService::getAsProductDto)
                    .collect(Collectors.toList());

        } else {
            products = productService.getAllProductsFiltered(name, priceFrom, priceTo, categoryId)
                    .stream()
                    .map(productMapperService::getAsProductDto)
                    .collect(Collectors.toList());

        }
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = productMapperService.getAsProductDto(productService.getProductById(id));

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody CreateProductDTO createProductDTO) {
        ProductDTO productDTO = productMapperService.getAsProductDto(productService.addProduct(createProductDTO));

        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id,
                                                    @RequestBody CreateProductDTO createProductDTO) {
        ProductDTO productDTO = productMapperService.getAsProductDto(productService.updateProduct(id, createProductDTO));

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") Long id){
        ProductDTO productDTO = productMapperService.getAsProductDto(productService.deleteProduct(id));

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
