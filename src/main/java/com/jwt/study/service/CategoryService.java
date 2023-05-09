package com.jwt.study.service;

import com.jwt.study.domain.Category;
import com.jwt.study.dto.request.CreateCategory;
import com.jwt.study.dto.response.CategoryResponse;
import com.jwt.study.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll(Long parentId) {
        List<Category> categories = categoryRepository.findCategoriesByParentId(parentId);
        return categories.stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public Long addCategory(CreateCategory createCategory){
        Category save = categoryRepository.save(createCategory.toEntity());
        return save.getId();
    }

}
