package com.example.CodeJudge.modelDTOs;

import com.example.CodeJudge.model.Category;
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
    Category category;

}
