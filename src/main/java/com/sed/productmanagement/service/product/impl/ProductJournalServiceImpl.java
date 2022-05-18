package com.sed.productmanagement.service.product.impl;

import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.config.Config;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.dao.ProductDao;
import com.sed.productmanagement.service.product.ProductJournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductJournalServiceImpl implements ProductJournalService {

    private final ProductDao productDao;

    private final Config config;

    @Override
    public Page<Product> getActiveVisibleProducts(int page) {
        logger.info("gonna get active and visible products. page: {}", page);
        return productDao.findAllByActiveIsTrueAndVisibleIsTrue(PageRequest.of(page, config.getProductBatchSize()));
    }

    @Override
    public Product findByActiveIsTrueAndVisibleIsTrueAndCodeIs(String code) throws GeneralException {
        logger.info("gonna get active and visible product by productCode. productCode: {}", code);
        return productDao.findByActiveIsTrueAndVisibleIsTrueAndCodeIs(code)
                .orElseThrow(() -> new GeneralException(Result.NOT_FOUND, "product not found"));
    }

    @Override
    public void save(Product product) {
        logger.info("gonna save product. product: {}", product);
        productDao.save(product);
    }


}
