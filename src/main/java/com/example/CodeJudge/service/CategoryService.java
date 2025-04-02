package com.example.CodeJudge.service;

import com.example.CodeJudge.modelDTOs.CategoryDTO;
import com.example.CodeJudge.modelDTOs.CategoryResponce;
import jakarta.validation.Valid;

public interface CategoryService {
    CategoryResponce getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(@Valid CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(@Valid Long categoryId);

    CategoryDTO updateCategory(@Valid CategoryDTO categoryDTO, Long categoryId);
}
