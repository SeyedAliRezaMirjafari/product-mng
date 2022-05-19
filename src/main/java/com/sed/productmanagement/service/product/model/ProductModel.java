package com.sed.productmanagement.service.product.model;

import com.sed.productmanagement.model.comment.Comment;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.ProductView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
public class ProductModel {

    private ProductView product;
    private Page<Comment> comments;

    public ProductModel(ProductView product) {
        this.product = product;
    }
}
