package com.sed.productmanagement.service.product.impl;

import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.ProductView;
import com.sed.productmanagement.service.comment.CommentService;
import com.sed.productmanagement.service.product.ProductJournalService;
import com.sed.productmanagement.service.product.ProductService;
import com.sed.productmanagement.service.product.mapper.ProductServiceBeanMapper;
import com.sed.productmanagement.service.product.model.ProductActionModel;
import com.sed.productmanagement.service.product.model.ProductModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJournalService productJournalService;
    private final CommentService commentService;
    private final ProductServiceBeanMapper mapper;

    @Override
    public ProductModel getProduct(String code) throws GeneralException {
        logger.info("gonna get full product detail. productCode: {}", code);
        ProductView product = productJournalService.findWithProvidersByActiveIsTrueAndVisibleIsTrueAndCodeIs(code);
        ProductModel productModel = new ProductModel(product);
        if (visibleComment(product)) {
            productModel.setComments(commentService.findLiteByProduct(product));
        }
        return productModel;
    }

    @Override
    public void updateProductUserAction(ProductActionModel productActionModel) throws GeneralException {
        Product product = productJournalService.findByCode(productActionModel.getProductCode());
        mapper.updateProductUserAction(product, productActionModel);
        productJournalService.save(product);
    }


    private boolean visibleComment(ProductView product) {
        return product.isPublicVisibleComment() /*|| buyeProduct*/;
    }
}
