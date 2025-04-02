package com.example.CodeJudge.service;

import com.example.CodeJudge.modelDTOs.SubmissionResponce;
import org.springframework.stereotype.Service;


public interface SubmissionService {

    SubmissionResponce getAllSubmissions(Long userId, Long problemId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
