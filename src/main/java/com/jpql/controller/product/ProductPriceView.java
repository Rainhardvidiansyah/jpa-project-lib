package com.jpql.controller.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.jpql.Repository.product.ProductRepo;
import com.jpql.entities.product.ProductEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/sorted")
public class ProductPriceView {

    private static final Logger log = LoggerFactory.getLogger(ProductPriceView.class);

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/product/price")
    public List<ProductEntity> getProductPrice(){
        List<ProductEntity> products = new ArrayList<>();
        List<ProductEntity> a = productRepo.findAll();
        List<ProductEntity> testing =
        a.stream().filter(b -> b.getPrice() < 20.0).collect(Collectors.toList());
        log.info("Get Price from 20.0 : {}", testing);
        return testing;
    }
    
}
