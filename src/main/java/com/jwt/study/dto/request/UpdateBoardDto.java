package com.jwt.study.dto.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data @ToString(of = {"contents"})
public class UpdateBoardDto {
    @NotNull
    private String contents;

    public UpdateBoardDto() {
    }

    public UpdateBoardDto(String contents) {
        this.contents = contents;
    }
}
