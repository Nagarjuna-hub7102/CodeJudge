package com.example.CodeJudge.controller;

import com.example.CodeJudge.modelDTOs.TestCaseDTO;
import com.example.CodeJudge.modelDTOs.TestCaseResponce;
import com.example.CodeJudge.service.TestCaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping("/admin/problem/{problemId}/testCase")
    public ResponseEntity<TestCaseDTO> addTestCase(@Valid @RequestBody TestCaseDTO testCaseDTO, @PathVariable Long problemId) {
        testCaseDTO.setProblemId(problemId);
        TestCaseDTO addedTestCaseDTO = testCaseService.addTestCase(testCaseDTO, problemId);
        return new ResponseEntity<>(addedTestCaseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/problem/{problemId}/testCases")
    public ResponseEntity<TestCaseResponce> getAllTestCases(@PathVariable Long problemId){
        TestCaseResponce testCaseResponce = testCaseService.getAllTestCases(problemId);
        return new ResponseEntity<>(testCaseResponce,HttpStatus.OK);
    }

    @PutMapping("/admin/problem/testCase/{testCaseId}")
    public ResponseEntity<TestCaseDTO> updateTestCase(@Valid @RequestBody TestCaseDTO testCaseDTO,@PathVariable Long testCaseId){
          TestCaseDTO updatedTestCaseDTO = testCaseService.updateTestCase(testCaseDTO,testCaseId);

          return new ResponseEntity<>(updatedTestCaseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/admin/problem/testCase/{testCaseId}")
    public ResponseEntity<TestCaseDTO> deleteTestCase(@PathVariable Long testCaseId){
        TestCaseDTO testCaseDTO = testCaseService.deleteTestCase(testCaseId);

        return new ResponseEntity<>(testCaseDTO,HttpStatus.OK);
    }




}
