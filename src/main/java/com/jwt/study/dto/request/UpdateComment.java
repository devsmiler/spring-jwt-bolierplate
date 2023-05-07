package com.jwt.study.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

@Data @ToString(of = {"contents"})
public class UpdateComment {
    @NotNull
    private String contents;

    public UpdateComment() {
    }

    public UpdateComment(String contents) {
        this.contents = contents;
    }
}
