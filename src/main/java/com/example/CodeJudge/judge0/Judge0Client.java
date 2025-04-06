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
        headers.set("X-RapidAPI-Key", apiKey); // or your API key

        Map<String, Object> body = new HashMap<>();
        body.put("source_code", code);
        body.put("language_id", getLanguageId(language));
        body.put("stdin", input);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Judge0Responce> response = restTemplate.postForEntity(JUDGE0_URL, request, Judge0Responce.class);

        return response.getBody();
    }

}
