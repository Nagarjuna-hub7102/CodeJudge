package com.example.CodeJudge.modelDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResult {
    private boolean success;
    private String message;
    private String failedInput;
    private String expectedOutput;
    private String actualOutput;
}
