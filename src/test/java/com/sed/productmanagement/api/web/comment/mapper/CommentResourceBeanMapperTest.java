package com.sed.productmanagement.api.web.comment.mapper;

import com.sed.productmanagement.common.model.CommentDTO;
import com.sed.productmanagement.component.mapper.Utils;
import com.sed.productmanagement.config.Config;
import com.sed.productmanagement.fake.CommentFake;
import com.sed.productmanagement.model.comment.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@Import({CommentResourceBeanMapperImpl.class, Utils.class})
public class CommentResourceBeanMapperTest {

    @Autowired
    private CommentResourceBeanMapper mapper;
    @Autowired
    private Utils utils;
    @MockBean
    private Config config;

    @Test
    void toCommentDto() {
        Comment comment = CommentFake.createComment();
        CommentDTO commentDTO = mapper.toCommentDto(comment);
        Assertions.assertEquals(commentDTO.getMessage(), comment.getMessage());
        Assertions.assertEquals(commentDTO.getUserName(), comment.getUserId());
        Assertions.assertEquals(commentDTO.getDate(), comment.getModificationStatusTime().toEpochMilli());
    }
}
