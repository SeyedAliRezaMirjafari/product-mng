package com.sed.productmanagement.service.product.model;

import com.sed.productmanagement.model.comment.Comment;
import com.sed.productmanagement.model.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
public class ProductModel {

    private Product product;
    private Page<Comment> comments;

    public ProductModel(Product product) {
        this.product = product;
    }
}
