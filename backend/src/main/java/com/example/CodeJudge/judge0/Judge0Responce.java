package com.example.CodeJudge.judge0;

import lombok.Data;

@Data
public class Judge0Responce {
    private String stdout;
    private String stderr;
    private String compileOutput;
    private Judge0Status status;

    @Data
    public static class Judge0Status {
        private int id;
        private String description;
    }
}
