package com.learnindi.todo.todospringboot.controller;

import com.learnindi.todo.todospringboot.entity.Task;
import com.learnindi.todo.todospringboot.repo.TaskRepository;
import com.learnindi.todo.todospringboot.search.SearchContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class about task
 *
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskRepository.findAll(Sort.by(Sort.Order.asc("title")));
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("Redundant parameter: id must be null!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTask(@RequestBody Task task) {
        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity<>("Missed parameter: id can't be null or zero!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        taskRepository.save(task);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long id) {
        return taskRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    String msgError = String.format("Task with this id=%s not found", id);
                    logger.error(msgError);
                    return new ResponseEntity(msgError, HttpStatus.NOT_ACCEPTABLE);
                });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        try {
            taskRepository.deleteById(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            String msgError = String.format("Task with this id=%s not found", id);
            logger.error(msgError, ex);
            return new ResponseEntity<String>(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> searchTask(@RequestBody SearchContainer<Task> container) {
        //todo: pretend NPE by Optional maybe
        var sort = Sort.by(Sort.Direction.fromString(container.getSortDirection()), container.getSortColumnName());

        var pageRequest = PageRequest.of(container.getPageNumber(), container.getPageSize(), sort);

        return ResponseEntity.ok(taskRepository.findByParameters(
                container.getSearchText(),
                container.isTaskStatus(),
                container.getCategoryId(),
                container.getPriorityId(),
                pageRequest
        ));
    }
}
