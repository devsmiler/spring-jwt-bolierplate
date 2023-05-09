package com.jwt.study.dto.response;

import com.jwt.study.domain.Category;
import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private Long parentId;
    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parentId = category.getParentId();
    }
}
