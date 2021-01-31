package com.learnindi.todo.todospringboot.controller;

import com.learnindi.todo.todospringboot.entity.Task;
import com.learnindi.todo.todospringboot.search.SearchContainer;
import com.learnindi.todo.todospringboot.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
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

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("Redundant parameter: id must be null!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskService.addTask(task));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTask(@RequestBody Task task) {
        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity<>("Missed parameter: id can't be null or zero!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        taskService.updateTask(task);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long id) {
        var taskFound = taskService.findTaskById(id);
        if (taskFound == null) {
            String msgError = String.format("Task with this id=%s not found", id);
            logger.error(msgError);
            return new ResponseEntity(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(taskFound);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        try {
            taskService.deleteTaskById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            String msgError = String.format("Task with this id=%s not found", id);
            logger.error(msgError, ex);
            return new ResponseEntity<>(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> searchTask(@RequestBody SearchContainer<Task> container) {
        return ResponseEntity.ok(taskService.searchTasks(container));
    }
}
