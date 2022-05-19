package com.sed.productmanagement.common.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class AddCommentRequest {
    @Length(max = 1000)
    private String message;
}
