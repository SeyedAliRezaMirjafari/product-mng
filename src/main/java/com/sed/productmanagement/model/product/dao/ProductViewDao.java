package com.sed.productmanagement.model.product.dao;

import com.sed.productmanagement.model.product.ProductView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductViewDao extends JpaRepository<ProductView, Long> {
    @Query("SELECT p FROM ProductView AS p where p.visible = true and p.active = true ")
    Page<ProductView> findAllByActiveIsTrueAndVisibleIsTrue(Pageable pageable);


    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "providers"
            }
    )
    @Query("SELECT DISTINCT p FROM ProductView AS p where p.code = :code and p.visible = true and p.active = true ")
    Optional<ProductView> findWithProvidersByActiveIsTrueAndVisibleIsTrueAndCodeIs(@Param("code") String code);
}
