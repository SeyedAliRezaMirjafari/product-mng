package com.sed.productmanagement.model.comment.dao;

import com.sed.productmanagement.model.comment.Comment;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {


    @Cacheable("commentLite")
    @Query("select c from Comment as c where c.status=1 and c.product.code = :productCode")
    Page<Comment> findPaginationByProduct(@Param("productCode") String productCode, Pageable pageable);

    @Cacheable("comment")
    @Query("select c from Comment as c where c.status=1 and c.product.code = :productCode")
    List<Comment> findByProduct(@Param("productCode") String productCode, Pageable pageable);


}
