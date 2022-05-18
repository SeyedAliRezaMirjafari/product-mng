package com.sed.productmanagement.service.product;

import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.product.Product;

import java.util.List;

public interface ProductJournalService {
    List<Product> getActiveVisibleProducts(int page);

    Product findByActiveIsTrueAndVisibleIsTrueAndCodeIs(String code) throws GeneralException;

    void save(Product product);
}
