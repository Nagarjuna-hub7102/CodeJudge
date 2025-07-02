package com.example.CodeJudge.judge0;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class Judge0Request {
    @JsonProperty("source_code")
    private String sourceCode;

    @JsonProperty("language_id")
    private Integer languageId;

    private String stdin;

    public Judge0Request(String code, String lang, String input) {
        this.sourceCode = code;
        this.languageId = LanguageMapper.getLanguageId(lang); // must return Integer now
        this.stdin = input;
    }

    // getters (optional but good for debugging/logging)
    public String getSourceCode() {
        return sourceCode;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public String getStdin() {
        return stdin;
    }
}
