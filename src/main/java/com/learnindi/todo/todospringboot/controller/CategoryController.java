package com.learnindi.todo.todospringboot.controller;

import com.learnindi.todo.todospringboot.entity.Category;
import com.learnindi.todo.todospringboot.repo.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class about category
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/test")
    public List<Category> test() {
        return categoryRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }
}
