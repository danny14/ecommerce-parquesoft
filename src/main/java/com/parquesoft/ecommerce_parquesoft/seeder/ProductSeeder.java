package com.parquesoft.ecommerce_parquesoft.seeder;

import com.parquesoft.ecommerce_parquesoft.entity.ProductEntity;
import com.parquesoft.ecommerce_parquesoft.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ProductSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductSeeder(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(productRepository.count() == 0){
            ProductEntity p1 = new ProductEntity();
            p1.setName("Portatil");
            p1.setPrice(10000);
            p1.setSku("7704521258");
            p1.setStock(10);


            ProductEntity p2 = new ProductEntity();
            p2.setName("PC ESCRITORIO");
            p2.setPrice(5000);
            p2.setSku("770444444");
            p2.setStock(50);

            productRepository.saveAll(List.of(p1,p2));

            System.out.println("Se Guardaron los datos correctamente");
        }else{
            System.out.println("No se guardaron los datos");
        }
    }
}
