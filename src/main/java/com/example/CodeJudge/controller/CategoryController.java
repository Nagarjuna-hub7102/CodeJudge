package com.example.CodeJudge.controller;

import com.example.CodeJudge.configuration.AppConstants;
import com.example.CodeJudge.modelDTOs.CategoryDTO;
import com.example.CodeJudge.modelDTOs.CategoryResponce;
import com.example.CodeJudge.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @GetMapping("/api/public/categories")
    // @RequestMapping(value = "/public/categories",method = RequestMethod.GET)
    public ResponseEntity<CategoryResponce> getAllCategories(
            @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder
    ){
        CategoryResponce categoryResponce =  categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponce, HttpStatus.OK);
    }


    @PostMapping("/api/admin/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO status = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(status,HttpStatus.CREATED);

    }


    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@Valid @PathVariable Long categoryId) {



        CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);

    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId){

        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);

    }

}
