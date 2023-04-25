package com.jwt.study.dto.request;

import com.jwt.study.domain.Board;
import com.jwt.study.domain.Member;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CreateBoard {
    @NotNull
    private String contents;
    private String writer;

    public Board toEntity(){
        return Board.builder()
                .contents(contents)
                .writer(writer)
                .build();
    }
}
