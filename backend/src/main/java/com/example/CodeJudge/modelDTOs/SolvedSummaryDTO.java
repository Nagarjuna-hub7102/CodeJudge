package com.example.CodeJudge.modelDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolvedSummaryDTO {
    private Long problemId;
    private String problemName;
    private String categoryName;
}
