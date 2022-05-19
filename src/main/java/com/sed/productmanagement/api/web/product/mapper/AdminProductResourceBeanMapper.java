package com.sed.productmanagement.api.web.product.mapper;

import com.sed.productmanagement.common.request.ProductUserActionRequest;
import com.sed.productmanagement.component.mapper.Utils;
import com.sed.productmanagement.service.product.model.ProductActionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {Utils.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AdminProductResourceBeanMapper {

    @Mapping(target = "active", source = "request.active")
    @Mapping(target = "publicVisibleComment", source = "request.publicVisibleComment")
    @Mapping(target = "commentable", source = "request.commentable")
    @Mapping(target = "visible", source = "request.visible")
    @Mapping(target = "votable", source = "request.votable")
    @Mapping(target = "productCode", source = "productCode")
    ProductActionModel toProductActionModel(ProductUserActionRequest request, String productCode);
}
