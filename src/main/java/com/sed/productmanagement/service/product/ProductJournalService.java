package com.sed.productmanagement.service.product;

import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.product.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductJournalService {
    Page<Product> getActiveVisibleProducts(int page);

    Product findByActiveIsTrueAndVisibleIsTrueAndCodeIs(String code) throws GeneralException;

    void save(Product product);
}
