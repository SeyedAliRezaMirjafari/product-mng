package com.sed.productmanagement.api.web.product.mapper;

import com.sed.productmanagement.common.model.ProductDTO;
import com.sed.productmanagement.component.mapper.Utils;
import com.sed.productmanagement.config.Config;
import com.sed.productmanagement.fake.ProductFake;
import com.sed.productmanagement.model.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@Import({ProductResourceBeanMapperImpl.class, Utils.class, Config.class})
public class ProductResourceBeanMapperTest {
    @Autowired
    private ProductResourceBeanMapper mapper;
    @Autowired
    private Utils utils;
    @Autowired
    private Config config;

    @Test
    void toProductDto() {
        Product product = ProductFake.createProduct();
        ProductDTO dto = mapper.toProductDto(product);
        Assertions.assertEquals(dto.getCode(), "code");
        Assertions.assertEquals(dto.getDescription(), "desc");
        Assertions.assertEquals(dto.getImage(), "http://file-server/file/image");
        Assertions.assertEquals(dto.getUrl(), "http://product-mng/product/code");
        Assertions.assertEquals(dto.getVoteSummary().getCount(), 12);
        Assertions.assertEquals(dto.getVoteSummary().getAverage(), 3.1);
        Assertions.assertEquals(dto.getTitle(), "title");
        Assertions.assertNull(dto.getCommentable());
        Assertions.assertNull(dto.getCommentSummary());
        Assertions.assertNull(dto.getPublicVisibleComment());
        Assertions.assertNull(dto.getVoteable());
        Assertions.assertNull(dto.getProviders());
    }

    @Test
    void toFullProductDto() {
        Product product = ProductFake.createProduct();
        ProductDTO dto = mapper.toFullProductDto(product);
        Assertions.assertEquals(dto.getCode(), "code");
        Assertions.assertEquals(dto.getDescription(), "desc");
        Assertions.assertEquals(dto.getImage(), "http://file-server/file/image");
        Assertions.assertEquals(dto.getUrl(), "http://product-mng/product/code");
        Assertions.assertEquals(dto.getVoteSummary().getCount(), 12);
        Assertions.assertEquals(dto.getVoteSummary().getAverage(), 3.1);
        Assertions.assertEquals(dto.getTitle(), "title");
        Assertions.assertEquals(dto.getCommentable(), ProductDTO.ActionTypeDTO.PUBLIC);
        Assertions.assertNull(dto.getCommentSummary());
        Assertions.assertEquals(dto.getPublicVisibleComment(), true);
        Assertions.assertEquals(dto.getVoteable(), ProductDTO.ActionTypeDTO.PUBLIC);
        Assertions.assertEquals(dto.getProviders().size(), 1);
        Assertions.assertEquals(dto.getProviders().get(0).getCode(), "code");
        Assertions.assertEquals(dto.getProviders().get(0).getName(), "name");
    }
}
