package com.example.CodeJudge.service;

import com.example.CodeJudge.execptions.ResourceNotFoundException;
import com.example.CodeJudge.judge0.Judge0Client;
import com.example.CodeJudge.judge0.Judge0Responce;
import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.Submission;
import com.example.CodeJudge.model.TestCase;
import com.example.CodeJudge.model.User;
import com.example.CodeJudge.modelDTOs.RunCodeRequest;
import com.example.CodeJudge.modelDTOs.RunCodeResponce;
import com.example.CodeJudge.modelDTOs.SubmissionResult;
import com.example.CodeJudge.openAI.OpenAIService;
import com.example.CodeJudge.repositories.ProblemRepository;
import com.example.CodeJudge.repositories.SubmissionRepository;
import com.example.CodeJudge.repositories.TestCaseRepository;
import com.example.CodeJudge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeJudgeServiceImpl implements CodeJudgeService{

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private Judge0Client judge0Client;

   @Autowired
   private OpenAIService openAIService;

    public List<RunCodeResponce> runUserCode(RunCodeRequest request) {
        Problem problem = problemRepository.findById(request.getProblemId())
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        List<TestCase> testCases = testCaseRepository.findTop2ByProblem_ProblemIdOrderByIdAsc(problem.getProblemId());

        List<RunCodeResponce> results = new ArrayList<>();

        for (TestCase testCase : testCases) {
            Judge0Responce judgeResponse = judge0Client.submit(
                    request.getCode(),
                    request.getLanguage(),
                    testCase.getInput()
            );

            String output = judgeResponse.getStdout();
            String error = judgeResponse.getCompileOutput() != null ? judgeResponse.getCompileOutput()
                    : judgeResponse.getStderr();

            boolean isCorrect = output != null && output.trim().equals(testCase.getExpectedOutput().trim());

            RunCodeResponce response = new RunCodeResponce();
            response.setInput(testCase.getInput());
            response.setExpectedOutput(testCase.getExpectedOutput());
            response.setActualOutput(output);
            response.setError(error);
            response.setStatus(judgeResponse.getStatus().getDescription());
            response.setIsCorrect(isCorrect);

            results.add(response);
        }

        return results;
    }

    @Override
    public SubmissionResult submitCode(RunCodeRequest request,String userEmail) {
        Problem problem = problemRepository.findById(request.getProblemId())
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<TestCase> testCases = openAIService.generateTestCasesForProblem(problem);

        boolean allPassed = true;
        String failedInput = null;
        String expectedOutput = null;
        String actualOutput = null;

        for (TestCase testCase : testCases) {
            Judge0Responce response = judge0Client.submit(
                    request.getCode(), testCase.getInput(), request.getLanguage()
            );

            String expected = testCase.getExpectedOutput().trim();
            String actual = response.getCompileOutput().trim();

            if (!expected.equals(actual)) {
                allPassed = false;
                failedInput = testCase.getInput();
                expectedOutput = expected;
                actualOutput = actual;
                break;
            }
        }

        // Save submission to DB
        Submission submission = new Submission();
        submission.setCode(request.getCode());
        submission.setSubmissionTime(LocalDateTime.now());
        submission.setAccepted(allPassed);
        submission.setUser(user);
        submission.setProblem(problem);
        submissionRepository.save(submission);

        if (allPassed) {
            return new SubmissionResult(true, "All test cases passed ✅", null, null, null);
        } else {
            return new SubmissionResult(false, "Test case failed ❌", failedInput, expectedOutput, actualOutput);
        }
    }
}
