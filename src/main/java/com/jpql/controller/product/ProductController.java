package com.jpql.controller.product;

import java.util.ArrayList;
import java.util.List;

import com.jpql.dto.product.ProductResponse;
import com.jpql.entities.product.ProductEntity;
import com.jpql.helper.SearchHandling;
import com.jpql.service.product.ProductService;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;


   

    /**
     THESE METHODS SHOULD SHOW:
     1. GET ALL PRODUCT -> ok
     2. GET PRODUCT BY ID -> ok
     3. GET PRODUCT BY NAME -> ok
     4. GET PRODUCT BY DESCRIPTION -> ok
     5. GET PRODUCT BY DESCRIPTION/PATHVARIABLE -> ok
     **/


     @GetMapping("/all")
     public ResponseEntity<?> testing(){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List <ProductEntity> product = productService.findAllProduct();
        List<ProductResponse> responses = new ArrayList<ProductResponse>();
        for(ProductEntity pr : product){
            ProductResponse productResponse = modelMapper.map(pr, ProductResponse.class);
            responses.add(productResponse);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
     }

     @GetMapping("/{id}")
     public ResponseEntity<?> findProductId(@PathVariable("id") Long productId){
         ProductEntity product = productService.findProductById(productId);
         ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
         return new ResponseEntity<>(productResponse, HttpStatus.OK);
     }

     @PostMapping("/search")
     public ResponseEntity<?> getByName(@RequestBody SearchHandling searchHandling){
         modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
       List<ProductEntity> productEntities = productService.getProductNameLike(searchHandling.getSearchData());
       List <ProductResponse> responses = new ArrayList<ProductResponse>();
       for(ProductEntity productEntity : productEntities){
           ProductResponse productResponse = modelMapper.map(productEntity, ProductResponse.class);
           responses.add(productResponse);
       }
       return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/description")
    public ResponseEntity<?> getByProductDescription(@RequestBody SearchHandling searchHandling){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
      List<ProductEntity> productEntities = productService.getProductDescription(searchHandling.getSearchData());
      List <ProductResponse> responses = new ArrayList<ProductResponse>();
      for(ProductEntity productEntity : productEntities){
          ProductResponse productResponse = modelMapper.map(productEntity, ProductResponse.class);
          responses.add(productResponse);
      }
      return new ResponseEntity<>(responses, HttpStatus.OK);
   }

   @GetMapping("/cek-description/{description}")
   public ResponseEntity<?> findAllDescription(@PathVariable("description") String productDescription){
       List<ProductEntity> productEntities = productService.getProductDescription(productDescription);
       List<ProductResponse> responses = new ArrayList<ProductResponse>();
       if(productEntities.isEmpty()){
           return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
       }
       for(ProductEntity productEntity : productEntities){
           ProductResponse productResponse = modelMapper.map(productEntity, ProductResponse.class);
           responses.add(productResponse);
       }
       return new ResponseEntity<>(responses, HttpStatus.OK);
   }

     
}
