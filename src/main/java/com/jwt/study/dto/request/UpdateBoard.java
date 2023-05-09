package com.jwt.study.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

@Data @ToString(of = {"contents"})
public class UpdateBoard {
    @NotNull
    private String contents;

    public UpdateBoard() {
    }

    public UpdateBoard(String contents) {
        this.contents = contents;
    }
}
