package com.learnindi.todo.todospringboot.repo;

import com.learnindi.todo.todospringboot.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface about task
 *
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT task FROM Task task WHERE" +
            "(:title IS NULL OR :title = '' OR lower(task.title) LIKE lower(concat('%', :title, '%')))" +
            "AND (:status IS NULL OR task.completed = :status)" +
            "AND (:categoryId IS NULL OR task.category.id = :categoryId)" +
            "AND (:priorityId IS NULL OR task.priority.id = :priorityId)"
    )
    Page<Task> findByParameters(
            @Param("title") String title,
            @Param("status") boolean completed,
            @Param("categoryId") Long categoryId,
            @Param("priorityId") Long priorityId,
            Pageable pageable);
}
