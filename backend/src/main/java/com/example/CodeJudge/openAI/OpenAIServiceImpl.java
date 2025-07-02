package com.example.CodeJudge.openAI;

import com.example.CodeJudge.execptions.ResourceNotFoundException;
import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.ProblemDescription;
import com.example.CodeJudge.model.TestCase;
import com.example.CodeJudge.repositories.ProblemDescriptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProblemDescriptionRepository problemDescriptionRepository;

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    @Override
    public List<TestCase> generateTestCasesForProblem(Problem problem) {
        ProblemDescription problemDescription = problemDescriptionRepository.findByProblem(problem)
                .orElseThrow(() -> new ResourceNotFoundException("ProblemDescription", "problem", problem.getProblemId()));

        String prompt = buildPrompt(problem, problemDescription.getConstraints());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a helpful assistant that generates JSON formatted test cases."),
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.7
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<OpenAIResponce> response = restTemplate.exchange(
                OPENAI_URL, HttpMethod.POST, request, OpenAIResponce.class
        );

        if (response.getBody() == null || response.getBody().getChoices().isEmpty()) {
            throw new RuntimeException("No response from OpenAI");
        }

        String content = response.getBody().getChoices().get(0).getMessage().getContent();

        try {
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(content, TestCase[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse test cases from OpenAI", e);
        }
    }


    private String buildPrompt(Problem problem,String Constraints) {
        return """
        Generate 10 test cases in JSON format for the following coding problem, including edge cases.

        Problem Title: %s
        Problem Description: %s
        Constraints: %s

        Return format should be:
        [
          { "input": "input1", "expectedOutput": "output1" },
          { "input": "input2", "expectedOutput": "output2" },
          ...
        ]
        """.formatted(problem.getProblemName(), problem.getProblemDescription(),Constraints);
    }
}
