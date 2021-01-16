package com.learnindi.todo.todospringboot.repo;

import com.learnindi.todo.todospringboot.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface about statistics
 *
 */
@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
}
