package com.parquesoft.ecommerce_parquesoft.controller;

import com.parquesoft.ecommerce_parquesoft.entity.ProductEntity;
import com.parquesoft.ecommerce_parquesoft.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productos", description = "Endpoints para la gestión de productos del E-Commerce")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista completa de todos los productos en la base de datos.")
    @GetMapping
    public List<ProductEntity> getAll() {
        return productService.getProducts();
    }

    @Operation(summary = "Buscar producto por SKU", description = "Devuelve un único producto que coincida con el SKU exacto.")
    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductEntity> getBySku(@PathVariable String sku) {
        return productService.getProductBySku(sku)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Filtrar productos por precio", description = "Retorna productos activos que estén dentro del rango de precio especificado.")
    @GetMapping("/search")
    public List<ProductEntity> search(
            @RequestParam(defaultValue = "0") double min, 
            @RequestParam(defaultValue = "999999") double max) {
        return productService.getActiveProductsByPrice(min, max);
    }

    @Operation(summary = "Crear un producto", description = "Crea un nuevo producto validando que el precio sea mayor a 0 y el stock no sea negativo.")
    @PostMapping
    public ResponseEntity<ProductEntity> create(@RequestBody ProductEntity product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @Operation(summary = "Actualizar un producto", description = "Modifica los datos de un producto existente usando su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable Long id, @RequestBody ProductEntity productDetails) {
        return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    }

    @Operation(summary = "Eliminar un producto", description = "Realiza un borrado lógico (desactivación) del producto.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
