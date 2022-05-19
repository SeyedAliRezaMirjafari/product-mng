package com.sed.productmanagement.service.product.mapper;

import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.service.product.model.ProductActionModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductServiceBeanMapper {


    default void updateProductUserAction(@MappingTarget Product product, ProductActionModel productActionModel) {
        product.setPublicVisibleComment(productActionModel.getPublicVisibleComment());
        product.setActive(productActionModel.getActive());
        product.setVisible(productActionModel.getVisible());
        product.setCommentable(productActionModel.getCommentable());
        product.setVotable(productActionModel.getVotable());
    }
}
