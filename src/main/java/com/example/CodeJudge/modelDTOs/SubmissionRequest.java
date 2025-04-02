package com.example.CodeJudge.modelDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionRequest {

        @NotBlank(message = "Code cannot be blank")
        private String code;

        @NotNull(message = "User ID is required")
        private Long userId;

        @NotNull(message = "Problem ID is required")
        private Long problemId;


}
