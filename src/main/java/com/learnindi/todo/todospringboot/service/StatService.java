package com.learnindi.todo.todospringboot.service;

import com.learnindi.todo.todospringboot.entity.Stat;
import com.learnindi.todo.todospringboot.repo.StatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class about statistics
 */
@Service
@Transactional
public class StatService {

    /**
     * Default ID for statistics record in DB
     */
    private static final long DEFAULT_ID = 1L;

    private final StatRepository statRepository;

    public StatService(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    public Stat getStatisticsById() {
        return statRepository.findById(DEFAULT_ID).orElse(null);
    }

}
