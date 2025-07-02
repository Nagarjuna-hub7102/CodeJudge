package com.example.CodeJudge.repositories;

import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase,Long> {
    boolean existsByInputAndExpectedOutputAndProblem(String input, String expectedOutput, Problem problem);

    List<TestCase> findByProblem(Problem problem);

    List<TestCase> findTop2ByProblem_ProblemIdOrderByIdAsc(Long problemId);


}
