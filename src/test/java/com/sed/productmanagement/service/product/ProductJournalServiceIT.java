package com.sed.productmanagement.service.product;

import com.sed.productmanagement.AbstractIT;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.fake.ProductFake;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.dao.ProductDao;
import com.sed.productmanagement.model.productproviders.dao.ProductProvidersDao;
import com.sed.productmanagement.model.provider.dao.ProviderDao;
import com.sed.productmanagement.model.vote.Vote;
import com.sed.productmanagement.model.vote.dao.VoteDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProductJournalServiceIT extends AbstractIT {

    @Autowired
    private ProductJournalService productJournalService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProviderDao providerDao;
    @Autowired
    private VoteDao voteDao;
    @Autowired
    private ProductProvidersDao productProvidersDao;

    @AfterEach
    @BeforeEach
    void clean() {
        voteDao.deleteAll();
        productProvidersDao.deleteAll();
        productDao.deleteAll();
        providerDao.deleteAll();
    }

    @Test
    void findByActiveIsTrueAndVisibleIsTrueAndCodeIs() throws GeneralException {
        initDbSingleRecord("code");
        Product product = productJournalService.findByActiveIsTrueAndVisibleIsTrueAndCodeIs("code");
        Assertions.assertEquals(product.getCode(), "code");
        //FIXME: add full assertions
    }

    @Test
    void findByActiveIsTrueAndVisibleIsTrueAndCodeIs_notFound() throws GeneralException {
        GeneralException generalException = assertThrows(GeneralException.class, () -> {
            productJournalService.findByActiveIsTrueAndVisibleIsTrueAndCodeIs("code");
        });
        Assertions.assertEquals(generalException.getResult(), Result.NOT_FOUND);
    }

    @Test
    void getActiveVisibleProducts() {
        initDb();
        Page<Product> products = productJournalService.getActiveVisibleProducts(0);
        Assertions.assertEquals(products.getContent().size(), 2);
        Assertions.assertEquals(products.getContent().get(0).getCode(), "code_1");
        Assertions.assertEquals(products.getContent().get(1).getCode(), "code_2");
        //FIXME: add full assertions

    }

    private void initDbSingleRecord(String code) {
        Product product = ProductFake.createProduct();
        product.setCode(code);
        productDao.save(product);
        Vote vote = new Vote();
        vote.setUserId("user-id");
        vote.setScore(3);
        vote.setStatus(Vote.Status.ACCEPTED);
        vote.setProduct(product);
        vote.setCreationTime(Instant.now());
        voteDao.save(vote);
    }

    private void initDb() {
        Product product = ProductFake.createProduct();
        product.setCode("code_1");
        productDao.save(product);
        Vote vote = new Vote();
        vote.setUserId("user-id");
        vote.setScore(3);
        vote.setStatus(Vote.Status.ACCEPTED);
        vote.setProduct(product);
        vote.setCreationTime(Instant.now());
        voteDao.save(vote);

        Product product1 = ProductFake.createProduct();
        product1.setCode("code_2");
        product1.getProviders().stream().forEach(provider -> product.setCode("code_1"));
        productDao.save(product1);
        Vote vote1 = new Vote();
        vote1.setUserId("user-id");
        vote1.setScore(3);
        vote1.setStatus(Vote.Status.ACCEPTED);
        vote1.setProduct(product1);
        vote1.setCreationTime(Instant.now());
        voteDao.save(vote1);
    }
}
