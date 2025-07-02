package com.example.CodeJudge.controller;

import com.example.CodeJudge.modelDTOs.ProblemDescriptionDTO;
import com.example.CodeJudge.service.ProblemDescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DescriptionController {
    @Autowired
    private ProblemDescriptionService problemDescriptionService;


    @GetMapping("/public/problem/{problemId}/description")
    public ResponseEntity<ProblemDescriptionDTO> getDescriptionByProblemId(@PathVariable Long problemId){
        ProblemDescriptionDTO problemDescriptionDTO = problemDescriptionService.getDescriptionByProblemId(problemId);
        return new ResponseEntity<>(problemDescriptionDTO, HttpStatus.OK);
    }

    @PostMapping("/admin/problem/problemDescription/{problemDescriptionId}")
    public ResponseEntity<ProblemDescriptionDTO> updateDescription(@Valid @RequestBody ProblemDescriptionDTO problemDescriptionDTO, @PathVariable Long problemDescriptionId){
       ProblemDescriptionDTO updatedProblemDescriptionDTO = problemDescriptionService.updateDescription(problemDescriptionDTO,problemDescriptionId);
        return new ResponseEntity<>(problemDescriptionDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/admin/problem/problemDescription/{problemDescriptionId}")
    public ResponseEntity<String> deleteProblemDescription(
            @PathVariable Long problemDescriptionId) {
        problemDescriptionService.deleteProblemDescription(problemDescriptionId);
        return ResponseEntity.ok("Problem description deleted successfully");
    }

}
