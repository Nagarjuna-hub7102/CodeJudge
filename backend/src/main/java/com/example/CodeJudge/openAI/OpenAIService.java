package com.example.CodeJudge.openAI;

import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.TestCase;



import java.util.List;


public interface OpenAIService {
     List<TestCase> generateTestCasesForProblem(Problem problem);
}
