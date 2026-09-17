package com.parquesoft.ecommerce_parquesoft.seeder;

import com.parquesoft.ecommerce_parquesoft.entity.CustomerEntity;
import com.parquesoft.ecommerce_parquesoft.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerSeeder implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public CustomerSeeder(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // Validación: Solo insertamos si la tabla de clientes está vacía
        if (customerRepository.count() == 0) {
            
            CustomerEntity c1 = new CustomerEntity();
            c1.setFullName("Juan Pérez");
            c1.setEmail("juan@correo.com");
            c1.setLoyaltyPoints(150); // Este será un cliente VIP
            c1.setActive(true);

            CustomerEntity c2 = new CustomerEntity();
            c2.setFullName("María López");
            c2.setEmail("maria@correo.com");
            c2.setLoyaltyPoints(50); // Cliente regular
            c2.setActive(true);

            CustomerEntity c3 = new CustomerEntity();
            c3.setFullName("Carlos Inactivo");
            c3.setEmail("carlos@correo.com");
            c3.setLoyaltyPoints(0);
            c3.setActive(false); // Cliente inactivo (Borrado Lógico)

            // Guardamos todos los clientes
            customerRepository.saveAll(List.of(c1, c2, c3));

            System.out.println("✅ CustomerSeeder: Clientes de prueba insertados correctamente.");
        }
    }
}
