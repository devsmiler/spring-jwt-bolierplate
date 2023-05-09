package com.jwt.study.controller;

import com.jwt.study.domain.Category;
import com.jwt.study.dto.request.CreateCategory;
import com.jwt.study.dto.response.CategoryResponse;
import com.jwt.study.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("")
    public void addCategory (
        @RequestBody CreateCategory createCategory
    ) {
        categoryService.addCategory(createCategory);
    }

    @GetMapping("")
    public List<CategoryResponse> findAll (
        @RequestParam(required = false) Long parentId
    ) {
        return categoryService.findAll(parentId);
    }
    @DeleteMapping("/{categoryId}")
    public void delete (
        @PathVariable Long categoryId
    ) {

    }
    @PutMapping("/{categoryId}")
    public void update (
        @PathVariable Long categoryId
    ) {

    }
}
