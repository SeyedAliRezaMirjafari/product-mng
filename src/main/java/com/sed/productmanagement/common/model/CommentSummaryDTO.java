package com.sed.productmanagement.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommentSummaryDTO {

    private Long count;

    private List<CommentDTO> comments;
}
