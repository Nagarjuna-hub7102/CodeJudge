package com.example.CodeJudge.controller;

import com.example.CodeJudge.configuration.AppConstants;
import com.example.CodeJudge.modelDTOs.SubmissionResponce;
import com.example.CodeJudge.service.SubmissionService;
import com.example.CodeJudge.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private AuthUtil authUtil;

    @GetMapping("/user/submissions")
    public ResponseEntity<SubmissionResponce> getAllSubmissions(
                                                @RequestParam(required = false) Long problemId,
                                                @RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                @RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                @RequestParam(defaultValue = AppConstants.SORT_SUBMISSIONS_BY,required = false) String sortBy,
                                                @RequestParam(defaultValue ="desc") String sortOrder){
        Long userId = authUtil.loggedInUserId();
        SubmissionResponce submissionResponce = submissionService.getAllSubmissions(userId,problemId,pageNumber,pageSize,sortBy,sortOrder);

        return new ResponseEntity<>(submissionResponce, HttpStatus.OK);
    }

}
