package com.sed.productmanagement.common.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {

    private String message;

    private Long date;

    private String userName;
}
