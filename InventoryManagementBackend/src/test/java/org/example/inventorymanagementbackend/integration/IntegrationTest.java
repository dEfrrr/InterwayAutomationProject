package org.example.inventorymanagementbackend.integration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.inventorymanagementbackend.dto.CreateProductDTO;
import org.example.inventorymanagementbackend.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void confirmProductCreatedSuccessfully() throws Exception {
        CreateProductDTO dto = new CreateProductDTO();
        dto.setName("Cake");
        dto.setDescription("Medium");
        dto.setCategory(0);
        dto.setQuantityInStock(10);
        dto.setPrice(50.0);
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated());
        String response = mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("Products returned: " + response);
    }

    @Test
    void confirmProductEditedSuccessfully() throws Exception {
        CreateProductDTO dto = new CreateProductDTO();
        dto.setName("Cake");
        dto.setDescription("Big");
        dto.setCategory(0);
        dto.setQuantityInStock(15);
        dto.setPrice(60.0);
        String createJson = objectMapper.writeValueAsString(dto);
        MvcResult createResult = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        String createResponse = createResult.getResponse().getContentAsString();
        ProductDTO createdProduct = objectMapper.readValue(createResponse, ProductDTO.class);
        Long productId = createdProduct.getId();
        CreateProductDTO updateDto = new CreateProductDTO("Chocolate Cake", createdProduct.getDescription(), createdProduct.getPrice(), createdProduct.getQuantityInStock(), createdProduct.getCategory().getId());
        String updateJson = objectMapper.writeValueAsString(updateDto);
        mockMvc.perform(put("/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult getResult = mockMvc.perform(get("/products/" + productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String getResponse = getResult.getResponse().getContentAsString();
        ProductDTO updatedProduct = objectMapper.readValue(getResponse, ProductDTO.class);
        assertEquals("Chocolate Cake", updatedProduct.getName());
    }
}