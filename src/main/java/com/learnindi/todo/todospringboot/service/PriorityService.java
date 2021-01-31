package com.learnindi.todo.todospringboot.service;

import com.learnindi.todo.todospringboot.entity.Priority;
import com.learnindi.todo.todospringboot.repo.PriorityRepository;
import com.learnindi.todo.todospringboot.search.SearchContainer;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class about priority
 */
@Service
@Transactional
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll(Sort.by(Sort.Order.asc("title")));
    }

    public Priority addPriority(@NonNull Priority priority) {
        return priorityRepository.save(priority);
    }

    public Priority updatePriority(@NonNull Priority priority) {
        return priorityRepository.save(priority);
    }

    @Nullable
    public Priority findPriorityById(@NonNull Long id) {
        return priorityRepository.findById(id).orElse(null);
    }

    public void deletePriorityById(@NonNull Long id) {
        priorityRepository.deleteById(id);
    }

    public List<Priority> searchPriorities(SearchContainer<Priority> container) {
        return priorityRepository.findByTitle(container.getSearchText());
    }
}
