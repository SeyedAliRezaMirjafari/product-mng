package com.sed.productmanagement.api.web.comment;

import com.sed.productmanagement.api.web.comment.mapper.CommentResourceBeanMapper;
import com.sed.productmanagement.common.request.AddCommentRequest;
import com.sed.productmanagement.common.response.base.ResponseServiceBase;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.common.response.comment.CommentListResponse;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.service.comment.CommentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/product/{productCode}/comment")
@RequiredArgsConstructor
@Validated
public class CommentResource {

    private final CommentService commentService;
    private final CommentResourceBeanMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentListResponse> getComments(
            @PathVariable(value = "productCode", required = true) String productCode,
            @RequestParam(name = "page", defaultValue = "0") int page) throws GeneralException {
        logger.info("going to get product's comment, productCode {}, page {}", productCode, page);
        return ResponseEntity.ok(mapper.toCommentListResponse(commentService.findByProductCode(productCode, page)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseServiceBase> addComment(
            @PathVariable(value = "productCode", required = true) String productCode,
            @RequestHeader(value = "USER-ID", required = true) String userId,
            @Valid @NonNull @RequestBody AddCommentRequest request) throws GeneralException {
        logger.info("going to add comment, productCode {}, userId {}", productCode, userId);
        commentService.addComment(mapper.toCommentModel(productCode, userId, request));
        return ResponseEntity.ok(new ResponseServiceBase(Result.SUCCESS));
    }
}
