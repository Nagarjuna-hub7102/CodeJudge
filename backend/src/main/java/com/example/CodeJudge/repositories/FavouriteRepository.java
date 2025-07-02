package com.example.CodeJudge.repositories;

import com.example.CodeJudge.model.Favourite;
import com.example.CodeJudge.model.Problem;
import com.example.CodeJudge.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite,Long> {

    Page<Problem> findProblemsByUser(User user, Pageable pageDetails);
    Optional<Favourite> findByUserAndProblem(User user, Problem problem);
}
