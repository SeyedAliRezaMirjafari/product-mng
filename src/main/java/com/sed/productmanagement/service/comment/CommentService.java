package com.sed.productmanagement.service.comment;

import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.comment.Comment;
import com.sed.productmanagement.model.product.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    List<Comment> findByProductCode(String productCode, int page) throws GeneralException;


    Page<Comment> findLiteByProduct(Product product);
}
