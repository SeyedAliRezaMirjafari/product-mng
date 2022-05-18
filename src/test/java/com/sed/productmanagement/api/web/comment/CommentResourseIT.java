package com.sed.productmanagement.api.web.comment;

import com.sed.productmanagement.AbstractIT;
import com.sed.productmanagement.common.model.CommentDTO;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.common.response.comment.CommentListResponse;
import com.sed.productmanagement.fake.CommentFake;
import com.sed.productmanagement.fake.ProductFake;
import com.sed.productmanagement.model.comment.Comment;
import com.sed.productmanagement.model.comment.dao.CommentDao;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.dao.ProductDao;
import com.sed.productmanagement.model.productproviders.dao.ProductProvidersDao;
import com.sed.productmanagement.model.provider.dao.ProviderDao;
import com.sed.productmanagement.util.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommentResourseIT extends AbstractIT {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProviderDao providerDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ProductProvidersDao productProvidersDao;

    @AfterEach
    @BeforeEach
    void clean() {
        commentDao.deleteAll();
        productProvidersDao.deleteAll();
        productDao.deleteAll();
        providerDao.deleteAll();
    }

    @Test
    void getComments() {
        inidDb();
        String uri = TestUtils.baseUrl(port) + TestUtils.PRODUCT + "/code/comment";
        HttpEntity entity = new HttpEntity<>(TestUtils.defaultHeaders());
        ResponseEntity<CommentListResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                CommentListResponse.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getBody().getResult(), Result.SUCCESS);
        List<CommentDTO> commentDTOS = responseEntity.getBody().getComments();
        Assertions.assertEquals(commentDTOS.size(), 1);
        //FIXME: add full assertions

    }


    private void inidDb() {
        Product product = ProductFake.createProduct();
        productDao.save(product);
        Comment comment = CommentFake.createComment();
        comment.setProduct(product);
        commentDao.save(comment);
    }


}
