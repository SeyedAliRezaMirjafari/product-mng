package com.sed.productmanagement.service.vote.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoteModel {
    private String productCode;
    private String userId;
    private Integer score;
}
