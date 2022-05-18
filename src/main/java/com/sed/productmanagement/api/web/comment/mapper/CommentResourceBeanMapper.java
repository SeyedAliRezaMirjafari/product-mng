package com.sed.productmanagement.api.web.comment.mapper;

import com.sed.productmanagement.common.model.CommentDTO;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.common.response.comment.CommentListResponse;
import com.sed.productmanagement.component.mapper.Utils;
import com.sed.productmanagement.model.comment.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Utils.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CommentResourceBeanMapper {

    List<CommentDTO> toCommentDtos(List<Comment> comments);

    @Mapping(target = "userName", source = "userId")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "date", source = "modificationStatusTime", qualifiedByName = "toLong")
    CommentDTO toCommentDto(Comment comment);

    default CommentListResponse toCommentListResponse(List<Comment> comments) {
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setComments(toCommentDtos(comments));
        commentListResponse.setResult(Result.SUCCESS);
        return commentListResponse;
    }
}
