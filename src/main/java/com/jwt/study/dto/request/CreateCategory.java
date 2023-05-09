package com.jwt.study.dto.request;

import com.jwt.study.domain.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateCategory {
    @NotNull @NotBlank
    private String name;
    private Long parentId;

    public Category toEntity(){
        return Category.builder().name(name).parentId(parentId).build();
    }
}
