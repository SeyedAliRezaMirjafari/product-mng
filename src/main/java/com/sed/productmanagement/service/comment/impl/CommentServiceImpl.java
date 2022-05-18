package com.sed.productmanagement.service.comment.impl;

import com.sed.productmanagement.config.Config;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.comment.Comment;
import com.sed.productmanagement.model.comment.dao.CommentDao;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.service.comment.CommentService;
import com.sed.productmanagement.service.product.ProductJournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    private final Config config;

    private final ProductJournalService productJournalService;


    @Override
    public List<Comment> findByProductCode(String productCode, int page) throws GeneralException {
        logger.info("gonna find comments by productCode. productCode: {}", productCode);
        Product product = productJournalService.findByActiveIsTrueAndVisibleIsTrueAndCodeIs(productCode);
        List<Comment> comments = null;
        if (visibleComment(product)) {
            logger.debug("visible comment for user");
            comments = commentDao.findByProduct(productCode, PageRequest.of(page, config.getCommentBatchSize(), Sort.by("modificationStatusTime").descending()));
        }
        return comments;
    }

    @Override
    public Page<Comment> findLiteByProduct(Product product) {
        logger.info("gonna find comments by product. productCode: {}", product.getCode());
        return commentDao.findPaginationByProduct(product.getCode(), PageRequest.of(0, config.getCommentShortListSize(), Sort.by("modificationStatusTime").descending()));
    }

    private boolean visibleComment(Product product) {
        //FIXME: external service
        return product.isPublicVisibleComment() /*|| buyeProduct*/;
    }
}
