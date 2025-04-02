package com.example.CodeJudge.controller;

import com.example.CodeJudge.configuration.AppConstants;
import com.example.CodeJudge.modelDTOs.FavouriteDTO;
import com.example.CodeJudge.modelDTOs.ProblemDTO;
import com.example.CodeJudge.modelDTOs.ProblemResponce;
import com.example.CodeJudge.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    @PostMapping("/admin/categories/{categoryId}/problem")
    public ResponseEntity<ProblemDTO> addProblem(@Valid @RequestBody ProblemDTO problemDTO,
                                                 @PathVariable Long categoryId){
        ProblemDTO savedProblemDTO = problemService.addProblem(categoryId, problemDTO);
        return new ResponseEntity<>(savedProblemDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/problems")
    public ResponseEntity<ProblemResponce> getProblems( @RequestParam(required = false) Long categoryId,
                                                        @RequestParam(required = false) String difficulty,
                                                        @RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                        @RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                        @RequestParam(defaultValue = AppConstants.SORT_PROBLEMS_BY,required = false) String sortBy,
                                                        @RequestParam(defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder){
        ProblemResponce problemResponce = problemService.getProblems(categoryId,difficulty,pageNumber,pageSize,sortBy,sortOrder);

        return new ResponseEntity<>(problemResponce,HttpStatus.OK);
    }

   /* @GetMapping("/public/problems")
    public ResponseEntity<ProblemResponce> getAllProblems(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PROBLEMS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        ProblemResponce problemResponce = problemService.getAllProblems(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(problemResponce,HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/problems")
    public ResponseEntity<ProblemResponce> getProblemsByCategory(@PathVariable Long categoryId,
                                                                 @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                 @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                 @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PROBLEMS_BY, required = false) String sortBy,
                                                                 @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        ProblemResponce problemResponce = problemService.searchByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(problemResponce, HttpStatus.OK);
    }

    */

    @GetMapping("/public/problems/keyword/{keyword}")
    public ResponseEntity<ProblemResponce> getProblemsByKeyword(@PathVariable String keyword,
                                                                @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PROBLEMS_BY, required = false) String sortBy,
                                                                @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        ProblemResponce problemResponce = problemService.searchProblemByKeyword(keyword, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(problemResponce, HttpStatus.FOUND);
    }

    @PutMapping("/admin/problems/{problemId}")
    public ResponseEntity<ProblemDTO> updateProblem(@Valid @RequestBody ProblemDTO problemDTO,
                                                    @PathVariable Long problemId){
        ProblemDTO updatedProblemDTO = problemService.updateProblem(problemId, problemDTO);
        return new ResponseEntity<>(updatedProblemDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/problem/{problemId}")
    public ResponseEntity<ProblemDTO> deleteProduct(@PathVariable Long problemId){
        ProblemDTO deletedProblem = problemService.deleteProblem(problemId);
        return new ResponseEntity<>(deletedProblem, HttpStatus.OK);
    }

    @GetMapping("/public/favourites/{userId}")
    public ResponseEntity<ProblemResponce> getAllUserFavorites(@PathVariable Long userId,
                                                               @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                               @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                               @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PROBLEMS_BY, required = false) String sortBy,
                                                               @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        ProblemResponce problemResponce = problemService.getAllUserFavourites(userId,pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(problemResponce,HttpStatus.OK);
    }


    @PostMapping("/toggle")
    public ResponseEntity<Void> toggleFavorite(
            @Valid @RequestBody FavouriteDTO request) {
        problemService.toggleFavorite(
                request.getUserId(),
                request.getProblemId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
