package com.sed.productmanagement.service.product.impl;

import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.config.Config;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.product.ProductView;
import com.sed.productmanagement.model.product.dao.ProductDao;
import com.sed.productmanagement.model.product.dao.ProductViewDao;
import com.sed.productmanagement.service.product.ProductJournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductJournalServiceImpl implements ProductJournalService {

    private final ProductDao productDao;

    private final ProductViewDao productViewDao;

    private final Config config;

    @Override
    public Page<ProductView> getActiveVisibleProducts(int page) {
        logger.info("gonna get active and visible products. page: {}", page);
        return productViewDao.findAllByActiveIsTrueAndVisibleIsTrue(PageRequest.of(page, config.getProductBatchSize()));
    }

    @Override
    public ProductView findWithProvidersByActiveIsTrueAndVisibleIsTrueAndCodeIs(String code) throws GeneralException {
        logger.info("gonna get active and visible product by productCode. productCode: {}", code);
        return productViewDao.findWithProvidersByActiveIsTrueAndVisibleIsTrueAndCodeIs(code)
                .orElseThrow(() -> new GeneralException(Result.NOT_FOUND, "product not found"));
    }

    @Override
    public Product findByActiveIsTrueAndVisibleIsTrueAndCodeIs(String code) throws GeneralException {
        logger.info("gonna get active and visible product by productCode. productCode: {}", code);
        return productDao.findByActiveIsTrueAndVisibleIsTrueAndCodeIs(code)
                .orElseThrow(() -> new GeneralException(Result.NOT_FOUND, "product not found"));
    }

    @Override
    public Product findByCode(String code) throws GeneralException {
        logger.info("gonna get product by productCode. productCode: {}", code);
        return productDao.findByCode(code)
                .orElseThrow(() -> new GeneralException(Result.NOT_FOUND, "product not found"));
    }

    @Override
    public void save(Product product) {
        logger.info("gonna save product. product: {}", product);
        productDao.save(product);
    }


}
