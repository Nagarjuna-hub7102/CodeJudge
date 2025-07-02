package com.example.CodeJudge.modelDTOs;

import com.example.CodeJudge.model.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseResponce {
    List<TestCaseDTO> testCaseDTOS;
    private int count;
}
