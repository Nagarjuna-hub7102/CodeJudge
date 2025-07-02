package com.example.CodeJudge.repositories;

import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.ProblemDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemDescriptionRepository extends JpaRepository<ProblemDescription,Long> {
    Optional<ProblemDescription> findByProblem(Problem problem);
}
