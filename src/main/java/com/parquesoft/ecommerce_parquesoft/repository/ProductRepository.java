package com.parquesoft.ecommerce_parquesoft.repository;

import com.parquesoft.ecommerce_parquesoft.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // 1. Query Method Automático
    Optional<ProductEntity> findBySku(String sku);

    // 2. Consulta JPQL Personalizada
    @Query("SELECT p FROM ProductEntity p WHERE p.active = true AND p.price >= :minPrice AND p.price <= :maxPrice")
    List<ProductEntity> findActiveProductsInPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
