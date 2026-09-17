package com.parquesoft.ecommerce_parquesoft.service;

import com.parquesoft.ecommerce_parquesoft.entity.ProductEntity;
import com.parquesoft.ecommerce_parquesoft.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    public List<ProductEntity> getActiveProductsByPrice(double minPrice, double maxPrice) {
        return productRepository.findActiveProductsInPriceRange(minPrice, maxPrice);
    }

    public ProductEntity createProduct(ProductEntity product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        product.setActive(true);
        return productRepository.save(product);
    }

    public ProductEntity updateProduct(Long id, ProductEntity details) {
        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID " + id));

        if (details.getPrice() <= 0 || details.getStock() < 0) {
            throw new IllegalArgumentException("Precios y stock deben ser válidos.");
        }

        existing.setName(details.getName());
        existing.setPrice(details.getPrice());
        existing.setSku(details.getSku());
        existing.setStock(details.getStock());
        
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID " + id));
        product.setActive(false);
        productRepository.save(product);
    }
}
