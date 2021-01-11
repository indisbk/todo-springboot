package com.learnindi.todo.todospringboot.repo;

import com.learnindi.todo.todospringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface about category
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
