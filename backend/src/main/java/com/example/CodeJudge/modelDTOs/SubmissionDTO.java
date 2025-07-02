package com.example.CodeJudge.modelDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO {
    private Long submissionId;
    private String problemName;
    private Boolean isAccepted;
    private LocalDateTime submissionTime;
}

