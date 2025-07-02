package com.example.CodeJudge.service;

import com.example.CodeJudge.execptions.APIException;
import com.example.CodeJudge.execptions.ResourceNotFoundException;
import com.example.CodeJudge.model.Category;
import com.example.CodeJudge.modelDTOs.CategoryDTO;
import com.example.CodeJudge.modelDTOs.CategoryResponce;
import com.example.CodeJudge.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryResponce getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageableDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageableDetails);
        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty()){
            throw new APIException("No categories exists");
        }

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponce categoryResponce = new CategoryResponce();

        categoryResponce.setContent(categoryDTOS);
        categoryResponce.setPageSize(categoryPage.getSize());
        categoryResponce.setPageNumber(categoryPage.getNumber());
        categoryResponce.setTotalElements(categoryPage.getTotalElements());
        categoryResponce.setTotalPages(categoryPage.getTotalPages());
        categoryResponce.setLast(categoryPage.isLast());

        return categoryResponce;
    }
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if(savedCategory!=null){
            throw new APIException("Category with"+category.getCategoryName()+"is already exists");
        }
        //category.setCategoryId(nextId++);
        Category savedCategory1 = categoryRepository.save(category);

        return modelMapper.map(savedCategory1, CategoryDTO.class);

    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()
                .filter(e->e.getCategoryId()
                        .equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryId",categoryId));




        categoryRepository.delete(category);

        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        List<Category> categories = categoryRepository.findAll();
        Optional<Category> optionalCategory = categories.stream()
                .filter(e -> e.getCategoryId().equals(categoryId))
                .findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedCategory = categoryRepository.save(existingCategory);
            return modelMapper.map(savedCategory, CategoryDTO.class);
        }

        else {
            throw new ResourceNotFoundException("category","categoryId",categoryId);
        }


    }
}
