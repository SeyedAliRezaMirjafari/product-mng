package com.sed.productmanagement.service.comment.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentModel {
    private String productCode;
    private String userId;
    private String message;
}
