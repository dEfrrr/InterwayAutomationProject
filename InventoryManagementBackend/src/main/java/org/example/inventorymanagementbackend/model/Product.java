package org.example.inventorymanagementbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.example.inventorymanagementbackend.model.enums.Category;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    @Min(0)
    private Double price;

    @Column(name = "quantity_in_stock", nullable = false)
    @Min(0)
    private Integer quantityInStock;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
}
