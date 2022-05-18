package com.sed.productmanagement.common.response.product;


import com.sed.productmanagement.common.model.ProductDTO;
import com.sed.productmanagement.common.response.base.ResponseServiceBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductListResponse extends ResponseServiceBase {
    private List<ProductDTO> products;
    private Long count;
    private Integer pageCount;
    private Integer page;
}
