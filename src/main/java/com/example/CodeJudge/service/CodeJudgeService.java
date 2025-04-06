package com.example.CodeJudge.service;

import com.example.CodeJudge.modelDTOs.RunCodeRequest;
import com.example.CodeJudge.modelDTOs.RunCodeResponce;
import com.example.CodeJudge.modelDTOs.SubmissionResult;
import jakarta.validation.constraints.Email;

import java.util.List;

public interface CodeJudgeService {

    List<RunCodeResponce> runUserCode(RunCodeRequest request);

    SubmissionResult submitCode(RunCodeRequest request ,String Email);
}
