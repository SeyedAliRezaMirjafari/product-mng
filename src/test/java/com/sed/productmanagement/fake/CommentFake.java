package com.sed.productmanagement.fake;

import com.sed.productmanagement.model.comment.Comment;

import java.time.Instant;

public class CommentFake {
    public static Comment createComment() {
        Comment comment = new Comment();
        comment.setMessage("msg");
        comment.setUserId("user-id");
        comment.setCreationTime(Instant.now());
        comment.changeStatus(Comment.Status.ACCEPTED);
        return comment;
    }
}
