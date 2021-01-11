package com.learnindi.todo.todospringboot.repo;

import com.learnindi.todo.todospringboot.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface about priority
 *
 */
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
