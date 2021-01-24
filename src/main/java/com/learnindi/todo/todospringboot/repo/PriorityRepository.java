package com.learnindi.todo.todospringboot.repo;

import com.learnindi.todo.todospringboot.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface about priority
 *
 */
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    List<Priority> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT priority FROM Priority priority WHERE " +
            ":title IS NULL OR :title = ' ' OR  lower(priority.title) LIKE lower(concat('%', :title, '%'))" +
            "ORDER BY priority.title ASC")
    List<Priority> findByTitle(@Param("title") String title);
}
