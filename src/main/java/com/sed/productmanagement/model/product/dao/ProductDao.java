package com.sed.productmanagement.model.product.dao;


import com.sed.productmanagement.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product AS p where p.visible = true and p.active = true ")
    Page<Product> findAllByActiveIsTrueAndVisibleIsTrue(Pageable pageable);


    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "providers"
            }
    )
    @Query("SELECT DISTINCT p FROM Product AS p where p.code = :code and p.visible = true and p.active = true ")
    Optional<Product> findByActiveIsTrueAndVisibleIsTrueAndCodeIs(@Param("code") String code);

}
