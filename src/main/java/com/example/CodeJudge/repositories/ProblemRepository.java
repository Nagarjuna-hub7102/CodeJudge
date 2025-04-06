package com.example.CodeJudge.repositories;

import com.example.CodeJudge.model.Category;
import com.example.CodeJudge.model.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ProblemRepository  extends JpaRepository<Problem,Long> {


    Page<Problem> findByCategoryOrderByDifficultyAsc(Category category, Pageable pageDetails);

    Page<Problem> findByProblemNameLikeIgnoreCase(String s, Pageable pageDetails);

   Optional<Problem> findByProblemName(String problemName);

    Page<Problem> findByDifficultyIgnoreCase(String difficulty, Pageable pageable);

    Page<Problem> findByCategory(Category category, Pageable pageable);

    Page<Problem> findByCategoryAndDifficultyIgnoreCase(Category category, String difficulty, Pageable pageable);


}
