package com.sed.productmanagement.common.response.product;


import com.sed.productmanagement.common.model.ProductDTO;
import com.sed.productmanagement.common.response.base.ResponseServiceBase;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse extends ResponseServiceBase {
    private ProductDTO product;
}
