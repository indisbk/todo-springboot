package com.learnindi.todo.todospringboot.service;

import com.learnindi.todo.todospringboot.entity.Category;
import com.learnindi.todo.todospringboot.repo.CategoryRepository;
import com.learnindi.todo.todospringboot.search.SearchContainer;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class about category
 */
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Order.asc("title")));
    }

    public Category addCategory(@NonNull Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(@NonNull Category category) {
        return categoryRepository.save(category);
    }

    @Nullable
    public Category findCategoryById(@NonNull Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategoryById(@NonNull Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> searchCategories(SearchContainer<Category> container) {
        return categoryRepository.findByTitle(container.getSearchText());
    }
}
