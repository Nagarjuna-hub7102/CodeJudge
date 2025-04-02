package com.example.CodeJudge.repositories;

import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.Submission;
import com.example.CodeJudge.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {

    Page<Submission> findByUserAndProblem(User user, Problem problem, Pageable pageable);

    Page<Submission> findByProblem(Problem problem, Pageable pageable);

    Page<Submission> findByUser(User user, Pageable pageable);
}
