package com.example.CodeJudge.controller;

import com.example.CodeJudge.configuration.AppConstants;
import com.example.CodeJudge.execptions.ResourceNotFoundException;
import com.example.CodeJudge.model.Submission;
import com.example.CodeJudge.modelDTOs.SolvedProblemCodeDTO;
import com.example.CodeJudge.modelDTOs.SolvedSummaryDTO;
import com.example.CodeJudge.modelDTOs.SubmissionResponce;
import com.example.CodeJudge.repositories.SubmissionRepository;
import com.example.CodeJudge.service.SubmissionService;
import com.example.CodeJudge.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubmissionController {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private AuthUtil authUtil;
    @GetMapping("/user/submissions")
    public ResponseEntity<SubmissionResponce> getAllSubmissions(
            @RequestParam(required = false) Long problemId,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_SUBMISSIONS_BY, required = false) String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {

        Long userId = authUtil.loggedInUserId();
        SubmissionResponce response = submissionService.getAllSubmissions(
                userId, problemId, pageNumber, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/submissions/{submissionId}")
    public ResponseEntity<String> getSubmissionCode(@PathVariable Long submissionId) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission", "id", submissionId));
        return ResponseEntity.ok(submission.getCode());
    }


    @GetMapping("/user/solved")
    public ResponseEntity<List<SolvedSummaryDTO>> getAllSolvedProblems(
            @RequestParam(required = false) Long problemId,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_SUBMISSIONS_BY, required = false) String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {

        Long userId = authUtil.loggedInUserId();
        List<SolvedSummaryDTO> response = submissionService.getSolvedProblemsByUser(userId, pageNumber, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/solved/{problemId}")
    public ResponseEntity<SolvedProblemCodeDTO> getLatestSolvedCode(@PathVariable Long problemId) {
        Long userId = authUtil.loggedInUserId();
        SolvedProblemCodeDTO response = submissionService.getLatestSolvedCode(userId, problemId);
        return ResponseEntity.ok(response);
    }



}
