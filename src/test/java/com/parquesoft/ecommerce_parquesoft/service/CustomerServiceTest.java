package com.parquesoft.ecommerce_parquesoft.service;

import com.parquesoft.ecommerce_parquesoft.entity.CustomerEntity;
import com.parquesoft.ecommerce_parquesoft.repository.CustomerRepository;
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
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerEntity validCustomer;

    @BeforeEach
    void setUp(){
        validCustomer = new CustomerEntity();
        validCustomer.setFullName("Luis Lozano");
        validCustomer.setEmail("luislozano@parquesoftti.com");
        validCustomer.setLoyaltyPoints(100);
    }

    @Test
    void testCreateCustomer_Success(){

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(validCustomer);

        CustomerEntity result = customerService.createCustomer(validCustomer);

        assertNotNull(result);
        assertTrue(result.getActive());
        assertEquals(100, result.getLoyaltyPoints());

        verify(customerRepository,times(1)).save(validCustomer);
    }

}
