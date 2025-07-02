package com.example.CodeJudge.judge0;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

import static com.example.CodeJudge.judge0.LanguageMapper.getLanguageId;

@Component
public class Judge0Client {
    @Autowired
     private RestTemplate restTemplate;
    private static final String JUDGE0_URL = "https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=false&wait=true";
    @Value("${judge0.api.key}")
    private String apiKey;

    public Judge0Responce submit(String code, String language, String input) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "judge0-ce.p.rapidapi.com"); // required by RapidAPI

        Judge0Request judge0Request = new Judge0Request(code, language, input);

        HttpEntity<Judge0Request> request = new HttpEntity<>(judge0Request, headers);

        try {
            System.out.println("📦 Submitting to Judge0:");
            System.out.println("Code: " + code);
            System.out.println("Language: " + language + " ➝ ID: " + LanguageMapper.getLanguageId(language));
            System.out.println("Input: " + input);

            ResponseEntity<Judge0Responce> response = restTemplate.postForEntity(
                    JUDGE0_URL, request, Judge0Responce.class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error calling Judge0 API: " + e.getMessage());
            return null;
        }


    }

}
