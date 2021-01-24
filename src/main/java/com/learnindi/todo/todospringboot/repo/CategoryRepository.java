package com.learnindi.todo.todospringboot.repo;

import com.learnindi.todo.todospringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface about category
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT category FROM Category category WHERE " +
            ":title IS NULL OR :title = ' ' OR  lower(category.title) LIKE lower(concat('%', :title, '%'))" +
            "ORDER BY category.title ASC")
    List<Category> findByTitle(@Param("title") String title);
}
