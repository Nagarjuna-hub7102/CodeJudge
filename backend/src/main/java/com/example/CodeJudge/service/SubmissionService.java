package com.example.CodeJudge.service;

import com.example.CodeJudge.modelDTOs.SolvedProblemCodeDTO;
import com.example.CodeJudge.modelDTOs.SolvedSummaryDTO;
import com.example.CodeJudge.modelDTOs.SubmissionResponce;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SubmissionService {

    SubmissionResponce getAllSubmissions(Long userId, Long problemId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    List<SolvedSummaryDTO> getSolvedProblemsByUser(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    SolvedProblemCodeDTO getLatestSolvedCode(Long userId, Long problemId);
}
