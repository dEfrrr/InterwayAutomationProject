package org.example.inventorymanagementbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {
    private String name;

    private String description;

    private Double price;

    private Integer quantityInStock;

    private int category;
}
