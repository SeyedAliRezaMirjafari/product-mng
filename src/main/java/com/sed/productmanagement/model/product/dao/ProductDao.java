package com.sed.productmanagement.model.product.dao;


import com.sed.productmanagement.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product AS p where p.code = :code and p.visible = true and p.active = true ")
    Optional<Product> findByActiveIsTrueAndVisibleIsTrueAndCodeIs(@Param("code") String code);

    Optional<Product> findByCode(String code);

}
