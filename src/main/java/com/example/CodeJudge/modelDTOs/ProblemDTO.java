package com.example.CodeJudge.modelDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProblemDTO {

    Long problemId;
    String problemName;
    String difficulty;

}
