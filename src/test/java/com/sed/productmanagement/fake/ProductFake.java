package com.sed.productmanagement.fake;

import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.ProductView;

import java.util.Set;

public class ProductFake {

    public static Product createProduct() {
        Product product = new Product();
        product.setImage("image");
        product.setTitle("title");
        product.setCode("code");
        product.setDescription("desc");
        product.setProviders(Set.of(ProviderFake.createProvider()));
        return product;
    }

    public static ProductView createProductView() {
        ProductView product = new ProductView();
        product.setImage("image");
        product.setTitle("title");
        product.setCode("code");
        product.setDescription("desc");
        product.setVoteAverage(3.1);
        product.setVoteCount(12L);
        product.setProviders(Set.of(ProviderFake.createProvider()));
        return product;
    }


}
