package com.jpql.entities.product;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.jpql.Repository.product.ProductRepo;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductDB implements CommandLineRunner{

    @Autowired(required = true)
    private ProductRepo productRepo;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProductDB.class);

    @Override
    public void run(String... args) throws Exception {
        productRepo.saveAll(List.of(
            new ProductEntity("Sambal Matah", "Khas Bali", 20.000, 300,
                LocalDate.of(2021, Month.OCTOBER, 20), LocalDate.of(2021, Month.NOVEMBER, 12)),
            new ProductEntity( "Sambal Bawang", "Khas Jawa Timur", 35.000, 211,
                LocalDate.of(2021, Month.DECEMBER, 5), LocalDate.of(2022, Month.FEBRUARY, 20)),
            new ProductEntity("Sambal Dabu-dabu", "Khas MANADO", 45.000, 156,
                LocalDate.of(2022, Month.JANUARY, 10), LocalDate.of(2022, Month.FEBRUARY, 20)),
            new ProductEntity("Sambal ABC", "Khas NASIONAL", 15.000, 50,
                LocalDate.of(2022, Month.JANUARY, 10), LocalDate.of(2022, Month.MARCH, 20)),
            new ProductEntity("Sambal JABAR", "Khas JAWA BARAT", 15.000, 87,
                LocalDate.of(2022, Month.FEBRUARY, 5), LocalDate.of(2022, Month.APRIL, 30)),
            new ProductEntity("Sambal JATENG", "Khas JAWA TENGAH", 100.000, 233,
                LocalDate.of(2022, Month.FEBRUARY, 5), LocalDate.of(2022, Month.MAY, 12)),
            new ProductEntity("Sambal DKI", "Khas JAKARTA", 60.000, 780,
                LocalDate.of(2022, Month.MARCH, 5), LocalDate.of(2022, Month.MAY, 31)) 
        ));



        LOGGER.info("DATA RECORDED");
        
    }
    
    
}
