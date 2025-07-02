package com.example.CodeJudge.modelDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunCodeResponce {
    private String input;
    private String expectedOutput;
    private String actualOutput;
    private String status;
    private String error;
    private Boolean isCorrect;
}
