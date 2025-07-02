package com.example.CodeJudge.repositories;

import com.example.CodeJudge.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);

    boolean existsByCategoryName(String name);
}
