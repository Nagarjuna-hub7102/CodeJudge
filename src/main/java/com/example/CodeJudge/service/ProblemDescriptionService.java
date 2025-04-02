package com.example.CodeJudge.service;

import com.example.CodeJudge.modelDTOs.ProblemDescriptionDTO;
import jakarta.validation.Valid;

public interface ProblemDescriptionService {
    ProblemDescriptionDTO getDescriptionByProblemId(Long problemId);

    ProblemDescriptionDTO updateDescription(@Valid ProblemDescriptionDTO problemDescriptionDTO, Long problemDescriptionId);

    void deleteProblemDescription(Long problemDescriptionId);
}
