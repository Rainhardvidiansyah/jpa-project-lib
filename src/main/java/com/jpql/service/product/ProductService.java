package com.jpql.service.product;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.transaction.Transactional;

import com.jpql.Repository.product.ProductRepo;
import com.jpql.entities.cart.CartEntity;
import com.jpql.entities.product.ProductEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {


    /* 
    METHOD THAT WILL BE CREATED HERE FUNCTION AS:
    SAVE PRODUCT 
    FIND ALL PRODUCT
    FIND PRODUCT BY ID
    FIND PRODUCT-LIKE
    FIND PRODUCT-BY-NAME
    FIND PRODUCT WITH ITS PRICES
    */

    @Autowired(required = true)
    private ProductRepo productRepo;


    public ProductEntity saveProduct(ProductEntity product){
        return productRepo.save(product);
    }

    public List<ProductEntity> findAllProduct(){
        return productRepo.findAll();
    }

    public ProductEntity findProductById(Long productId){
        Optional<ProductEntity> findId = productRepo.findById(productId);
        if(!findId.isPresent()){
            return null;
        }else{
            return productRepo.findById(productId).get();
        }
    }

    //METHOD USING JPQL
    public List<ProductEntity> getProductName(String productName){
        return productRepo.findByName(productName);
    }

    public List<ProductEntity> getProductNameLike(String productName){
        return productRepo.findProductNameLike(productName);
    }

    public List<ProductEntity> getProductDescription(String productDescription){
        return productRepo.findByProductDescription(productDescription);
    }
    
    
}






/**
 SET CART INTO PRODUCT
    public void setCartIdToProduct(Long productId, CartEntity cartEntity){
        ProductEntity productEntity = findProductById(productId);
        if(productEntity == null){
            ProductEntity product = new ProductEntity();
            getProductName(product.getProductName());
        }
        CartEntity cart = new CartEntity();
        productEntity.setCartEntity(cart);

        saveProduct(productEntity);
    }
 */
    