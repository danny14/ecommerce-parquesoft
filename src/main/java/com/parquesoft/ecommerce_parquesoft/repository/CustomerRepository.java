package com.parquesoft.ecommerce_parquesoft.repository;

import com.parquesoft.ecommerce_parquesoft.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByEmail(String email);

    @Query("SELECT c FROM CustomerEntity c WHERE c.active = true AND c.loyaltyPoints > :minPoints")
    List<CustomerEntity> findActiveVIPCustomers(@Param("minPoints") Integer minPoints);
}
