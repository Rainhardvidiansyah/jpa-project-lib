package com.jpql.entities.product;

import java.time.LocalDate;
import java.time.Month;
import java.util.Base64;
import java.util.List;

import org.springframework.core.io.Resource;

import com.jpql.Repository.product.ProductRepo;

import lombok.SneakyThrows;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ProductDB implements CommandLineRunner{

    @Autowired(required = true)
    private ProductRepo productRepo;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProductDB.class);

    @SneakyThrows
    private String getPicture(String location){
        Resource resource = resourceLoader.getResource(location);
        byte[] bytess = resource.getInputStream().readAllBytes();
        String pic = Base64.getEncoder().encodeToString(bytess);
        return pic;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepo.saveAll(List.of(
            new ProductEntity("Sambal Matah", getPicture("classpath:static/picture/sambal-1.jpg"), "Khas Bali", 20.500, 300, LocalDate.of(2021, Month.OCTOBER, 20), LocalDate.of(2021, Month.NOVEMBER, 12)),
            new ProductEntity( "Sambal Bawang", getPicture("classpath:static/picture/sambal-2.jpg"), "Khas Jawa Timur", 35.200, 211, LocalDate.of(2021, Month.DECEMBER, 5), LocalDate.of(2022, Month.FEBRUARY, 20)),
            new ProductEntity("Sambal Dabu-dabu", getPicture("classpath:static/picture/sambal-3.jpg"), "Khas MANADO", 45.000, 156, LocalDate.of(2022, Month.JANUARY, 10), LocalDate.of(2022, Month.FEBRUARY, 20)),
            new ProductEntity("Sambal ABC", getPicture("classpath:static/picture/sambal-3.jpg"), "Khas NASIONAL", 15.000, 50, LocalDate.of(2022, Month.JANUARY, 10), LocalDate.of(2022, Month.MARCH, 20)),
            new ProductEntity("Sambal JABAR", getPicture("classpath:static/picture/sambal-4.jpg"), "Khas JAWA BARAT", 15.000, 87, LocalDate.of(2022, Month.FEBRUARY, 5), LocalDate.of(2022, Month.APRIL, 30)),
            new ProductEntity("Sambal JATENG", getPicture("classpath:static/picture/sambal-5.jpg"), "Khas JAWA TENGAH", 100.000, 233, LocalDate.of(2022, Month.FEBRUARY, 5), LocalDate.of(2022, Month.MAY, 12)),
            new ProductEntity("Sambal DKI", getPicture("classpath:static/picture/sambal-5.jpg"), "Khas JAKARTA", 60.000, 780, LocalDate.of(2022, Month.MARCH, 5), LocalDate.of(2022, Month.MAY, 31)) 
        ));
        LOGGER.info("DATA PRODUCT RECORDED");
        
    }
    
    
}
