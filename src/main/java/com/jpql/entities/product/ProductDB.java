package com.jpql.entities.product;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.jpql.Repository.product.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductDB implements CommandLineRunner{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void run(String... args) throws Exception {
        productRepo.saveAll(List.of(
            new ProductEntity(1l, "Sambal Matah", "Khas Bali", 20.000, 300,
                LocalDate.of(2021, Month.OCTOBER, 20), LocalDate.of(2021, Month.NOVEMBER, 12)),
            new ProductEntity(2L, "Sambal Bawang", "Khas Jawa Timur", 35.000, 211,
                LocalDate.of(2021, Month.DECEMBER, 5), LocalDate.of(2022, Month.FEBRUARY, 20)),
            new ProductEntity(3L, "Sambal Dabu-dabu", "Khas Manado", 45.000, 156,
                LocalDate.of(2022, Month.JANUARY, 10), LocalDate.of(2022, Month.FEBRUARY, 20)),
            new ProductEntity(4L, "Sambal ABC", "Khas Nasional", 15.000, 50,
                LocalDate.of(2022, Month.JANUARY, 10), LocalDate.of(2022, Month.FEBRUARY, 20))
        ));

        System.out.println("DATA RECORDED");
    }
    
    
}
