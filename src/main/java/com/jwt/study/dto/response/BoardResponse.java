package com.jwt.study.dto.response;

import com.jwt.study.domain.Board;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponse {
    private Long id;
    private String writer;
    private String contents;
    private Integer commentCount;

    @Builder
    public BoardResponse(Long id, String writer, String contents) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }
    @QueryProjection
    public BoardResponse(Board board) {
        this.id = board.getId();
        this.writer = board.getWriter();
        this.contents = board.getContents();
        this.commentCount = board.getCommentCount();
    }

}
