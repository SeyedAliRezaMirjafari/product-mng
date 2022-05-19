package com.sed.productmanagement.api.web.product.mapper;


import com.sed.productmanagement.common.model.CommentDTO;
import com.sed.productmanagement.common.model.CommentSummaryDTO;
import com.sed.productmanagement.common.model.ProductDTO;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.common.response.product.ProductListResponse;
import com.sed.productmanagement.common.response.product.ProductResponse;
import com.sed.productmanagement.component.mapper.Utils;
import com.sed.productmanagement.model.comment.Comment;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.ProductView;
import com.sed.productmanagement.service.product.model.ProductModel;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", uses = {Utils.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductResourceBeanMapper {

    @Named("liteProductDto")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "commentable", ignore = true)
    @Mapping(target = "votable", ignore = true)
    @Mapping(target = "providers", ignore = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "image", source = "image", qualifiedByName = "toFileUrl")
    @Mapping(target = "url", source = "code", qualifiedByName = "toProductUrl")
    @Mapping(target = "publicVisibleComment", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "voteSummary.count", source = "voteCount")
    @Mapping(target = "voteSummary.average", source = "voteAverage")
    @Mapping(target = "commentSummary", ignore = true)
    ProductDTO toProductDto(ProductView productView);

    @IterableMapping(qualifiedByName = "liteProductDto")
    List<ProductDTO> toProductDtos(List<ProductView> productViews);

    default ProductListResponse toProductListResponse(Page<ProductView> productViews) {
        ProductListResponse productListResponse = new ProductListResponse();
        productListResponse.setProducts(toProductDtos(productViews.getContent()));
        productListResponse.setCount(productViews.getTotalElements());
        productListResponse.setPageCount(productViews.getTotalPages());
        productListResponse.setPage(productViews.getNumber());
        productListResponse.setResult(Result.SUCCESS);
        return productListResponse;
    }

    @Mapping(target = "code", source = "code")
    @Mapping(target = "commentable", source = "commentable")
    @Mapping(target = "votable", source = "votable")
    @Mapping(target = "providers", source = "providers")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "image", source = "image", qualifiedByName = "toFileUrl")
    @Mapping(target = "url", source = "code", qualifiedByName = "toProductUrl")
    @Mapping(target = "publicVisibleComment", source = "publicVisibleComment")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "voteSummary.count", source = "voteCount")
    @Mapping(target = "voteSummary.average", source = "voteAverage")
    @Mapping(target = "commentSummary", ignore = true)
    ProductDTO toFullProductDto(ProductView productView);

    default ProductResponse toProductResponse(ProductModel productModel) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProduct(toFullProductDto(productModel.getProduct()));
        productResponse.getProduct().setCommentSummary(toCommentSummaryDto(productModel.getComments()));
        productResponse.setResult(Result.SUCCESS);
        return productResponse;
    }


    @Named("toCommentSummary")
    default CommentSummaryDTO toCommentSummaryDto(Page<Comment> comments) {
        if (Objects.nonNull(comments)) {
            CommentSummaryDTO commentSummaryDTO = new CommentSummaryDTO();
            commentSummaryDTO.setCount(comments.getTotalElements());
            commentSummaryDTO.setComments(toCommentDTOs(comments.getContent()));
            return commentSummaryDTO;
        }
        return null;
    }

    @Mapping(target = "date", source = "modificationStatusTime", qualifiedByName = "toLong")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "userName", source = "userId")
    CommentDTO toCommentDto(Comment comment);

    List<CommentDTO> toCommentDTOs(List<Comment> comments);


}
