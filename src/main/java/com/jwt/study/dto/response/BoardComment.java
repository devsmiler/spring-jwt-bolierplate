package com.jwt.study.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BoardComment {
    private BoardResponse boardResponse;
    private List<CommentResponse> commentResponseList;

    public BoardComment(BoardResponse boardResponse, List<CommentResponse> commentResponseList) {
        this.boardResponse = boardResponse;
        this.commentResponseList = commentResponseList;
    }
}
