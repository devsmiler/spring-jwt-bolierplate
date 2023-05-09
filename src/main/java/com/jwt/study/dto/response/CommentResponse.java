package com.jwt.study.dto.response;

import com.jwt.study.domain.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String writer;
    private String contents;

    public CommentResponse (Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter();
        this.contents = comment.getContents();
    }
}
