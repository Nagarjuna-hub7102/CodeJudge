package com.example.CodeJudge.controller;

import com.example.CodeJudge.configuration.AppConstants;
import com.example.CodeJudge.modelDTOs.SubmissionResponce;
import com.example.CodeJudge.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping("/public/submissions")
    public ResponseEntity<SubmissionResponce> getAllSubmissions(@RequestParam(required = false) Long userId,
                                                @RequestParam(required = false) Long problemId,
                                                @RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                @RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                @RequestParam(defaultValue = AppConstants.SORT_SUBMISSIONS_BY,required = false) String sortBy,
                                                @RequestParam(defaultValue ="desc") String sortOrder){
        SubmissionResponce submissionResponce = submissionService.getAllSubmissions(userId,problemId,pageNumber,pageSize,sortBy,sortOrder);

        return new ResponseEntity<>(submissionResponce, HttpStatus.OK);
    }
}
