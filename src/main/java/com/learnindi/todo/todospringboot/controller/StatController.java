package com.learnindi.todo.todospringboot.controller;

import com.learnindi.todo.todospringboot.entity.Stat;
import com.learnindi.todo.todospringboot.service.StatService;
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

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<Stat> getStatistics() {
        Stat foundStatistics = statService.getStatisticsById();
        if (foundStatistics == null) {
            return new ResponseEntity("Statistics is empty", HttpStatus.ACCEPTED);
        }
        return ResponseEntity.ok(foundStatistics);
    }
}
