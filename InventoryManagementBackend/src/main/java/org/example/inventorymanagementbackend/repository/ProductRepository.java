package org.example.inventorymanagementbackend.repository;

import org.example.inventorymanagementbackend.model.Product;
import org.example.inventorymanagementbackend.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p from Product p " +
            "WHERE (:name IS NULL OR p.name ILIKE '%' || :name || '%' )" +
            "AND (:priceFrom IS NULL OR p.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR p.price <= :priceTo) " +
            "AND (:category IS NULL OR p.category = :category)")
    List<Product> getAllProductsFiltered(String name, Double priceFrom, Double priceTo, Category category);

}
