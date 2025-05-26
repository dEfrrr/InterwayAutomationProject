package org.example.inventorymanagementbackend.unit;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.inventorymanagementbackend.dto.CategoryDTO;
import org.example.inventorymanagementbackend.dto.CreateProductDTO;
import org.example.inventorymanagementbackend.model.Product;
import org.example.inventorymanagementbackend.model.enums.Category;
import org.example.inventorymanagementbackend.repository.ProductRepository;
import org.example.inventorymanagementbackend.service.CategoryService;
import org.example.inventorymanagementbackend.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;

public class UnitTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, categoryService);
    }

    @Test
    void verifyProductByIdShouldReturnProductWhenProductExists() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Cake");
        product.setCategory(Category.FOOD);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Product found = productService.getProductById(productId);
        assertNotNull(found);
        assertEquals(productId, found.getId());
        assertEquals("Cake", found.getName());
        assertEquals(Category.FOOD, found.getCategory());
        verify(productRepository).findById(productId);
    }

    @Test
    void addProductShouldSaveAndReturnProduct() {
        CreateProductDTO dto = new CreateProductDTO();
        dto.setName("Big Cake");
        dto.setDescription("Delicious");
        dto.setPrice(50.0);
        dto.setQuantityInStock(15);
        dto.setCategory(0);
        CategoryDTO categoryDTO = new CategoryDTO(0, "Food");
        when(categoryService.getAllCategories()).thenReturn(List.of(categoryDTO));
        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName(dto.getName());
        savedProduct.setDescription(dto.getDescription());
        savedProduct.setPrice(dto.getPrice());
        savedProduct.setQuantityInStock(dto.getQuantityInStock());
        savedProduct.setCategory(Category.fromDisplayName(categoryDTO.getCategoryName()));
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        Product result = productService.addProduct(dto);
        assertNotNull(result);
        assertEquals(savedProduct.getId(), result.getId());
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(dto.getPrice(), result.getPrice());
        assertEquals(dto.getQuantityInStock(), result.getQuantityInStock());
        assertEquals(Category.fromDisplayName(categoryDTO.getCategoryName()), result.getCategory());
        verify(productRepository).save(any(Product.class));
    }
}