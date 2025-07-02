package com.example.CodeJudge.service;

import com.example.CodeJudge.modelDTOs.TestCaseDTO;
import com.example.CodeJudge.modelDTOs.TestCaseResponce;
import jakarta.validation.Valid;

public interface TestCaseService {

    TestCaseDTO addTestCase(@Valid TestCaseDTO testCaseDTO, Long problemId);

    TestCaseResponce getAllTestCases(Long problemId);

    TestCaseDTO updateTestCase(@Valid TestCaseDTO testCaseDTO, Long testCaseId);

    TestCaseDTO deleteTestCase(Long testCaseId);
}
