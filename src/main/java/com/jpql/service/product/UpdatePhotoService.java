package com.jpql.service.product;

import com.jpql.Repository.product.ProductRepo;
import com.jpql.entities.product.ProductEntity;

import org.springframework.stereotype.Service;

@Service
public class UpdatePhotoService {

    private ProductRepo productRepo;

    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    public void updatePhoto(Long id, ProductEntity productEntity){
        ProductEntity productEntity2 = productRepo.findById(id).orElse(null);
        productRepo.save(productEntity);
    }

    
    
}
