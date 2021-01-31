package com.learnindi.todo.todospringboot.controller;

import com.learnindi.todo.todospringboot.entity.Priority;
import com.learnindi.todo.todospringboot.search.SearchContainer;
import com.learnindi.todo.todospringboot.service.PriorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class about priority
 *
 */
@RestController
@RequestMapping("/priority")
public class PriorityController {

    private final Logger logger = LoggerFactory.getLogger(PriorityController.class);

    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/all")
    public List<Priority> getAllPriorities() {
        return priorityService.getAllPriorities();
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> addPriority(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("Redundant parameter: id must be null!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Missed parameter: color", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.addPriority(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePriority(@RequestBody Priority priority) {
        if (priority.getId() == null || priority.getId() == 0) {
            return new ResponseEntity<>("Missed parameter: id can't be null or zero!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity<>("Missed parameter: color", HttpStatus.NOT_ACCEPTABLE);
        }

        priorityService.updatePriority(priority);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findPriorityById(@PathVariable Long id) {
        Priority foundPriority = priorityService.findPriorityById(id);
        if (foundPriority == null) {
            String msgError = String.format("Priority with this id=%s not found", id);
            logger.error(msgError);
            return new ResponseEntity(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(foundPriority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePriorityById(@PathVariable Long id) {
        try {
            priorityService.deletePriorityById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            String msgError = String.format("Priority with this id=%s not found", id);
            logger.error(msgError, ex);
            return new ResponseEntity<>(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> searchPriority(@RequestBody SearchContainer<Priority> container) {
        return ResponseEntity.ok(priorityService.searchPriorities(container));
    }
}
