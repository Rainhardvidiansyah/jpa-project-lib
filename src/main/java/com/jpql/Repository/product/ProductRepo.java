package com.jpql.Repository.product;

import java.util.List;

import com.jpql.entities.product.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepo extends JpaRepository<ProductEntity, Long>{

    @Query("select p from ProductEntity p where p.productName = :productName")
    List <ProductEntity> findByName(@Param("productName") String productName);

    //@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
    @Query("select p from ProductEntity p where p.productName like %:productName%")
    List<ProductEntity> findProductNameLike(String productName);



    
}
