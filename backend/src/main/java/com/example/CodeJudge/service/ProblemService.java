package com.example.CodeJudge.service;

import com.example.CodeJudge.modelDTOs.FavouriteDTO;
import com.example.CodeJudge.modelDTOs.ProblemDTO;
import com.example.CodeJudge.modelDTOs.ProblemResponce;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ProblemService {
    ProblemDTO addProblem(Long categoryId, @Valid ProblemDTO problemDTO);

    ProblemResponce getAllProblems(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProblemResponce searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProblemResponce searchProblemByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProblemDTO updateProblem(Long problemId, @Valid ProblemDTO problemDTO);

    ProblemDTO deleteProblem(Long problemId);

    ProblemResponce getProblems(Long categoryId, String difficulty, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProblemResponce getAllUserFavourites(Long userId,Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    boolean toggleFavorite(@NotNull Long userId, @NotNull Long problemId);
}
