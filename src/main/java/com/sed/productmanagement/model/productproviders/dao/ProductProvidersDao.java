package com.sed.productmanagement.model.productproviders.dao;


import com.sed.productmanagement.model.productproviders.ProductProviders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductProvidersDao extends JpaRepository<ProductProviders, Long> {
}
