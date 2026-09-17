package com.parquesoft.ecommerce_parquesoft.service;

import com.parquesoft.ecommerce_parquesoft.entity.CustomerEntity;
import com.parquesoft.ecommerce_parquesoft.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<CustomerEntity> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<CustomerEntity> getVIPCustomers(Integer minPoints) {
        return customerRepository.findActiveVIPCustomers(minPoints);
    }

    public CustomerEntity createCustomer(CustomerEntity customer) {
        if (customer.getLoyaltyPoints() == null || customer.getLoyaltyPoints() < 0) {
            customer.setLoyaltyPoints(0);
        }
        customer.setActive(true);
        return customerRepository.save(customer);
    }

    public CustomerEntity updateCustomer(Long id, CustomerEntity details) {
        CustomerEntity existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID " + id));

        existing.setFullName(details.getFullName());
        existing.setEmail(details.getEmail());
        if (details.getLoyaltyPoints() != null && details.getLoyaltyPoints() >= 0) {
            existing.setLoyaltyPoints(details.getLoyaltyPoints());
        }
        
        return customerRepository.save(existing);
    }

    public void deleteCustomer(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID " + id));
        customer.setActive(false);
        customerRepository.save(customer);
    }
}
