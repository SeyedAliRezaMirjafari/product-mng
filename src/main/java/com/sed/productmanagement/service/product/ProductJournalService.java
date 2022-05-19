package com.sed.productmanagement.service.product;

import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.ProductView;
import org.springframework.data.domain.Page;

public interface ProductJournalService {
    Page<ProductView> getActiveVisibleProducts(int page);

    ProductView findWithProvidersByActiveIsTrueAndVisibleIsTrueAndCodeIs(String code) throws GeneralException;

    Product findByActiveIsTrueAndVisibleIsTrueAndCodeIs(String code) throws GeneralException;

    Product findByCode(String code) throws GeneralException;

    void save(Product product);
}
