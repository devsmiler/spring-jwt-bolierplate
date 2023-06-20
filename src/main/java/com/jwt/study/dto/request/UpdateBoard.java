package com.jwt.study.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data @ToString(of = {"contents"})
public class UpdateBoard {
    @NotNull//
    @NotBlank(message = "빈값은 들어오면 안됩니다.")
    private String contents; // content:""

    public UpdateBoard() {
    }

    public UpdateBoard(String contents) {
        this.contents = contents;
    }
}
