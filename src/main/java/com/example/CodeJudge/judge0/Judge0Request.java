package com.example.CodeJudge.judge0;

public class Judge0Request {
    private String source_code;
    private String language_id;
    private String stdin;

    public Judge0Request(String code, String langId, String input) {
        this.source_code = code;
        this.language_id = LanguageMapper.getLanguageId(langId);
        this.stdin = input;
    }
}
