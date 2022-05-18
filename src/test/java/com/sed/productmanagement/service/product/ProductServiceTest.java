package com.sed.productmanagement.service.product;

import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.fake.CommentFake;
import com.sed.productmanagement.fake.ProductFake;
import com.sed.productmanagement.model.comment.Comment;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.service.comment.CommentService;
import com.sed.productmanagement.service.product.impl.ProductServiceImpl;
import com.sed.productmanagement.service.product.model.ProductModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith({SpringExtension.class})
@Import({ProductServiceImpl.class})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private ProductJournalService productJournalService;


    @Test
    void getProduct_successful() throws GeneralException {
        doReturn(createProduct()).when(productJournalService).findByActiveIsTrueAndVisibleIsTrueAndCodeIs("code");
        doReturn(createComments()).when(commentService).findLiteByProduct(any());
        ProductModel productModel = productService.getProduct("code");
        Assertions.assertNotNull(productModel);
        Assertions.assertNotNull(productModel.getProduct());
        Assertions.assertNotNull(productModel.getComments());
    }

    @Test
    void getProduct_commentVisibleFalse_successful() throws GeneralException {
        Product product = createProduct();
        product.setPublicVisibleComment(false);
        doReturn(product).when(productJournalService).findByActiveIsTrueAndVisibleIsTrueAndCodeIs("code");
        doReturn(createComments()).when(commentService).findLiteByProduct(any());
        ProductModel productModel = productService.getProduct("code");
        Assertions.assertNotNull(productModel);
        Assertions.assertNotNull(productModel.getProduct());
        Assertions.assertNull(productModel.getComments());
    }

    private Product createProduct() {
        Product product = ProductFake.createProduct();
        product.setId(1L);
        return product;
    }

    private Page<Comment> createComments() {
        return new PageImpl<Comment>(List.of(CommentFake.createComment()));
    }
}
