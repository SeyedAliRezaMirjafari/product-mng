package com.sed.productmanagement.service.comment;

import com.sed.productmanagement.AbstractIT;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.fake.CommentFake;
import com.sed.productmanagement.fake.ProductFake;
import com.sed.productmanagement.model.comment.Comment;
import com.sed.productmanagement.model.comment.dao.CommentDao;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.dao.ProductDao;
import com.sed.productmanagement.model.productproviders.dao.ProductProvidersDao;
import com.sed.productmanagement.model.provider.dao.ProviderDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentServiceIT extends AbstractIT {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProviderDao providerDao;

    @Autowired
    private ProductProvidersDao productProvidersDao;

    @AfterEach
    @BeforeEach
    void clean() {
        productProvidersDao.deleteAll();
        commentDao.deleteAll();
        productDao.deleteAll();
        providerDao.deleteAll();
    }

    @Test
    void findByProductCode() throws GeneralException {
        initDb();
        List<Comment> comments = commentService.findByProductCode("code", 0);
        Assertions.assertEquals(comments.size(), 1);
        Assertions.assertEquals(comments.get(0).getMessage(), "msg");
        //FIXME: add all assertions
    }

    @Test
    void findByProductCode_productNotFound() {
        GeneralException generalException = assertThrows(GeneralException.class, () -> {
            commentService.findByProductCode("code", 0);
        });
        Assertions.assertEquals(generalException.getResult(), Result.NOT_FOUND);
    }


    private void initDb() {
        Product product = ProductFake.createProduct();
        productDao.save(product);
        Comment comment = CommentFake.createComment();
        comment.setProduct(product);
        commentDao.save(comment);
    }
}
