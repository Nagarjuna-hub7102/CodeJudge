package com.example.CodeJudge.repositories;

import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.Submission;
import com.example.CodeJudge.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {

    Page<Submission> findByUserAndProblem(User user, Problem problem, Pageable pageable);

    Page<Submission> findByProblem(Problem problem, Pageable pageable);

    Page<Submission> findByUser(User user, Pageable pageable);

    @Query(
            value = """
        SELECT DISTINCT p.problem_id, p.problem_name, c.category_name
        FROM submission s
        JOIN problems p ON s.problem_id = p.problem_id
        JOIN categories c ON p.category_id = c.category_id
        WHERE s.user_id = :userId AND s.is_accepted = true
        """,
            countQuery = """
        SELECT COUNT(DISTINCT p.problem_id)
        FROM submission s
        JOIN problems p ON s.problem_id = p.problem_id
        WHERE s.user_id = :userId AND s.is_accepted = true
        """,
            nativeQuery = true
    )
    Page<Object[]> findSolvedProblemsByUser(@Param("userId") Long userId, Pageable pageable);

    @Query(value = """
    SELECT *
    FROM submission
    WHERE user_id = :userId AND problem_id = :problemId AND is_accepted = true
    ORDER BY submission_time DESC
    LIMIT 1
    """, nativeQuery = true)
    Submission findLatestAcceptedSubmissionByUserAndProblem(
            @Param("userId") Long userId,
            @Param("problemId") Long problemId
    );


}
