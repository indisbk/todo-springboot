package com.learnindi.todo.todospringboot.controller;

import com.learnindi.todo.todospringboot.entity.Priority;
import com.learnindi.todo.todospringboot.repo.PriorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class about priority
 *
 */
@RestController
@RequestMapping("/priority")
public class PriorityController {

    private Logger logger = LoggerFactory.getLogger(PriorityController.class);

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/priorities")
    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll(Sort.by(Sort.Order.asc("title")));
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

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> updatePriority(@RequestBody Priority priority) {
        if (priority.getId() == null || priority.getId() == 0) {
            return new ResponseEntity("Missed parameter: id can't be null or zero!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Missed parameter: color", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findPriorityById(@PathVariable Long id) {
        Optional<Priority> optionalPriority = priorityRepository.findById(id);
        if (optionalPriority.isPresent()) {
            return ResponseEntity.ok(optionalPriority.get());
        } else  {
            String msgError = String.format("Priority with this id=%s not found", id);
            logger.error(msgError);
            return new ResponseEntity(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePriorityById(@PathVariable Long id) {
        try {
            priorityRepository.deleteById(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            String msgError = String.format("Priority with this id=%s not found", id);
            logger.error(msgError, ex);
            return new ResponseEntity<String>(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
