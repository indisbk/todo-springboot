package com.learnindi.todo.todospringboot.service;

import com.learnindi.todo.todospringboot.entity.Task;
import com.learnindi.todo.todospringboot.repo.TaskRepository;
import com.learnindi.todo.todospringboot.search.SearchContainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class about task
 */
@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll(Sort.by(Sort.Order.asc("title")));
    }

    public Task addTask(@NonNull Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(@NonNull Task task) {
        return taskRepository.save(task);
    }

    @Nullable
    public Task findTaskById(@NonNull Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void deleteTaskById(@NonNull Long id) {
        taskRepository.deleteById(id);
    }

    public Page<Task> searchTasks(SearchContainer<Task> container) {
        var sort = Sort.by(Sort.Direction.fromString(container.getSortDirection()), container.getSortColumnName());

        var pageRequest = PageRequest.of(container.getPageNumber(), container.getPageSize(), sort);

        return taskRepository.findByParameters(
                container.getSearchText(),
                container.isTaskStatus(),
                container.getCategoryId(),
                container.getPriorityId(),
                pageRequest
        );
    }
}
