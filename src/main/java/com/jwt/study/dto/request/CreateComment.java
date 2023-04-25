package com.jwt.study.dto.request;

import com.jwt.study.domain.Comment;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateComment {
    @NotNull
    private String contents;
    private String writer;

    public Comment toEntity(){
        return Comment.builder()
                .contents(contents)
                .writer(writer)
                .build();
    }
}
