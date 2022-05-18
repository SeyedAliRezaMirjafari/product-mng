package com.sed.productmanagement.common.response.comment;


import com.sed.productmanagement.common.model.CommentDTO;
import com.sed.productmanagement.common.response.base.ResponseServiceBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CommentListResponse extends ResponseServiceBase {
    private List<CommentDTO> comments;
}
