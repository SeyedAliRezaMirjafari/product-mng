package com.sed.productmanagement.service.product.model;

import com.sed.productmanagement.model.product.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductActionModel {
    private Boolean active;
    private Boolean visible;
    private Product.ActionType votable;
    private Product.ActionType commentable;
    private Boolean publicVisibleComment;
    private String productCode;
}
