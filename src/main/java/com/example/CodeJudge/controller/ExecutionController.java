package com.example.CodeJudge.controller;

import com.example.CodeJudge.modelDTOs.RunCodeRequest;
import com.example.CodeJudge.modelDTOs.RunCodeResponce;
import com.example.CodeJudge.modelDTOs.SubmissionResult;
import com.example.CodeJudge.service.CodeJudgeService;
import com.example.CodeJudge.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ExecutionController {

    @Autowired
    private CodeJudgeService codeJudgeService;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/code/run")
    public ResponseEntity<List<RunCodeResponce>> runCode(@RequestBody RunCodeRequest request) {
        List<RunCodeResponce> responses = codeJudgeService.runUserCode(request);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/code/submit")
    public ResponseEntity<SubmissionResult> submitCode(@RequestBody RunCodeRequest request){
        String Email = authUtil.loggedInEmail();
        SubmissionResult submissionResult = codeJudgeService.submitCode(request,Email);

        return new ResponseEntity<>(submissionResult, HttpStatus.CREATED);
    }

}
