package com.learnindi.todo.todospringboot.controller;

import com.learnindi.todo.todospringboot.entity.Stat;
import com.learnindi.todo.todospringboot.repo.StatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class about statistics
 *
 */
@RestController
public class StatController {

    /**
     * Default ID for statistics record in DB
     */
    private static final long DEFAULT_ID = 1L;

    private final StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @GetMapping("/statistics")
    public ResponseEntity<Stat> getStatistics() {
        return statRepository
                .findById(DEFAULT_ID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity("Statistics is empty", HttpStatus.ACCEPTED));
    }
}
