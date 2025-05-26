package org.example.inventorymanagementbackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer quantityInStock;

    private CategoryDTO category;
}
