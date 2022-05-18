package com.sed.productmanagement.api.web.product;

import com.sed.productmanagement.AbstractIT;
import com.sed.productmanagement.common.model.ProductDTO;
import com.sed.productmanagement.common.response.base.ErrorResponse;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.common.response.product.ProductListResponse;
import com.sed.productmanagement.common.response.product.ProductResponse;
import com.sed.productmanagement.fake.ProductFake;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.dao.ProductDao;
import com.sed.productmanagement.model.productproviders.dao.ProductProvidersDao;
import com.sed.productmanagement.model.provider.dao.ProviderDao;
import com.sed.productmanagement.model.vote.Vote;
import com.sed.productmanagement.model.vote.dao.VoteDao;
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

import java.time.Instant;
import java.util.List;


public class ProductResourceIT extends AbstractIT {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
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
        productDao.deleteAll();
        providerDao.deleteAll();
    }


    @Test
    void getAllProducts() {
        initDb();
        String uri = TestUtils.baseUrl(port) + TestUtils.PRODUCT;
        HttpEntity entity = new HttpEntity<>(TestUtils.defaultHeaders());
        ResponseEntity<ProductListResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                ProductListResponse.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getBody().getResult(), Result.SUCCESS);
        List<ProductDTO> products = responseEntity.getBody().getProducts();
        Assertions.assertEquals(products.size(), 2);
        //FIXME: add full assertions
    }

    @Test
    void getProduct() {
        initDb();
        String uri = TestUtils.baseUrl(port) + TestUtils.PRODUCT + "/code_1";
        HttpEntity entity = new HttpEntity<>(TestUtils.defaultHeaders());
        ResponseEntity<ProductResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                ProductResponse.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getBody().getResult(), Result.SUCCESS);
        ProductDTO products = responseEntity.getBody().getProduct();
        Assertions.assertEquals(products.getCode(), "code_1");
        //FIXME: add full assertions
    }

    @Test
    void getProduct_notFound() {
        String uri = TestUtils.baseUrl(port) + TestUtils.PRODUCT + "/code";
        HttpEntity entity = new HttpEntity<>(TestUtils.defaultHeaders());
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                ErrorResponse.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
        Assertions.assertEquals(responseEntity.getBody().getResult(), Result.NOT_FOUND);
        //FIXME: add full assertions
    }


    private void initDb() {
        Product product = ProductFake.createProduct();
        product.setCode("code_1");
        productDao.save(product);
        Product product1 = ProductFake.createProduct();
        product1.setCode("code_2");
        product1.getProviders().stream().forEach(provider -> product.setCode("code_1"));
        productDao.save(product1);
    }
}
