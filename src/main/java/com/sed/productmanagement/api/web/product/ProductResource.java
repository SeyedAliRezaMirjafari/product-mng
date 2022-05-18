package com.sed.productmanagement.api.web.product;

import com.sed.productmanagement.api.web.product.mapper.ProductResourceBeanMapper;
import com.sed.productmanagement.common.response.product.ProductListResponse;
import com.sed.productmanagement.common.response.product.ProductResponse;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.service.product.ProductJournalService;
import com.sed.productmanagement.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    private final ProductResourceBeanMapper mapper;

    private final ProductJournalService productJournalService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductListResponse> getAllProducts(@RequestParam(name = "page", defaultValue = "0") int page) {
        logger.info("going to get all products. page {}", page);
        return ResponseEntity.ok(mapper.toProductListResponse(productJournalService.getActiveVisibleProducts(page)));
    }

    @GetMapping(path = "/{productCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(name = "productCode", required = true) String productCode) throws GeneralException {
        logger.info("going to get product's detail. productCode {}", productCode);
        return ResponseEntity.ok(mapper.toProductResponse(productService.getProduct(productCode)));
    }

}
