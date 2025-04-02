package com.example.CodeJudge.modelDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDTO {
    private Long id;

    @NotBlank(message = "Input cannot be blank")
    private String input;

    @NotBlank(message = "Expected output cannot be blank")
    private String expectedOutput;

    @NotNull(message = "Problem ID cannot be null")
    private Long problemId;

    private boolean isSample;
}