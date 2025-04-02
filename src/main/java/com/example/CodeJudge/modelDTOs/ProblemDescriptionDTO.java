package com.example.CodeJudge.modelDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDescriptionDTO {
    private Long problemDescriptionId;
    private String problemName;
    private String problemStatement;
    private String sampleInput;
    private String sampleOutput;
    private String constraints;

}
