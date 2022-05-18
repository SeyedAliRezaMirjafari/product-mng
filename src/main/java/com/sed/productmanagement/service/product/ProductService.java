package com.sed.productmanagement.service.product;

import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.service.product.model.ProductModel;

public interface ProductService {

    ProductModel getProduct(String code) throws GeneralException;
}
