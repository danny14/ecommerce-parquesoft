package com.parquesoft.ecommerce_parquesoft.service;

import com.parquesoft.ecommerce_parquesoft.entity.ProductEntity;
import com.parquesoft.ecommerce_parquesoft.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductEntity validProduct;

    @BeforeEach
    void setUp() {
        validProduct = new ProductEntity();
        validProduct.setName("Producto Test");
        validProduct.setPrice(100.0);
        validProduct.setStock(10);
        validProduct.setSku("TEST-001");
    }

    @Test
    void testCreateProduct_Success() {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(validProduct);
        ProductEntity result = productService.createProduct(validProduct);
        assertNotNull(result);
        assertTrue(result.getActive());
        verify(productRepository, times(1)).save(validProduct);
    }

    @Test
    void testCreateProduct_ThrowsException_WhenPriceIsNegative() {
        validProduct.setPrice(-50.0);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, 
                () -> productService.createProduct(validProduct)
        );
        assertEquals("Error: El precio debe ser mayor a 0", exception.getMessage());
        verify(productRepository, never()).save(any());
    }
}
