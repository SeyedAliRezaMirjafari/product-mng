package com.sed.productmanagement.api.web.product;

import com.sed.productmanagement.api.web.product.mapper.AdminProductResourceBeanMapper;
import com.sed.productmanagement.common.request.ProductUserActionRequest;
import com.sed.productmanagement.common.response.base.ResponseServiceBase;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.product.dao.ProductDao;
import com.sed.productmanagement.model.product.dao.ProductViewDao;
import com.sed.productmanagement.service.product.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductResource {

    private final ProductService productService;
    private final AdminProductResourceBeanMapper mapper;

    private final ProductDao productDao;
    private final ProductViewDao productViewDao;

    @PutMapping(path = "/update-user-action/{productCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseServiceBase> updateProductUserAction(
            @PathVariable(name = "productCode", required = true) String productCode,
            @NonNull @Valid @RequestBody ProductUserActionRequest request) throws GeneralException {
        logger.info("going to update product user action. productCode {}, new user action: {}", productCode, request);
        productService.updateProductUserAction(mapper.toProductActionModel(request, productCode));
        return ResponseEntity.ok(new ResponseServiceBase(Result.SUCCESS));
    }
}
