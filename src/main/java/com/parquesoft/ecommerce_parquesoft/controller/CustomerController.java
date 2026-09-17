package com.parquesoft.ecommerce_parquesoft.controller;

import com.parquesoft.ecommerce_parquesoft.entity.CustomerEntity;
import com.parquesoft.ecommerce_parquesoft.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cliente", description = "Endpoints para la gestión de clientes del E-Commerce")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista completa de todos los clientes en la base de datos.")
    @GetMapping
    public List<CustomerEntity> getAll() {
        return customerService.getCustomers();
    }

    @Operation(summary = "Buscar cliente por email", description = "Devuelve un único cliente que coincida con el email exacto.")
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerEntity> getByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar cliente que pertenezcan al VIP", description = "Devuelve todos los clientes que sean VIP que tengan mas de 100 puntos.")
    @GetMapping("/vip")
    public List<CustomerEntity> getVIPs(@RequestParam(defaultValue = "100") Integer minPoints) {
        return customerService.getVIPCustomers(minPoints);
    }

    @Operation(summary = "Crear cliente", description = "Crea un nuevo cliente validando que los puntos no sean negativos")
    @PostMapping
    public ResponseEntity<CustomerEntity> create(@RequestBody CustomerEntity customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    @Operation(summary = "Actualiza cliente", description = "Actualiza los datos de un cliente por medio del ID")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> update(@PathVariable Long id, @RequestBody CustomerEntity customerDetails) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDetails));
    }

    @Operation(summary = "Elimina un cliente", description = "Elimina todos los datos de un cliente por medio del ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
