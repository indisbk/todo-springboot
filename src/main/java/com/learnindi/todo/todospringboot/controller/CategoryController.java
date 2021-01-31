package com.learnindi.todo.todospringboot.controller;

import com.learnindi.todo.todospringboot.entity.Category;
import com.learnindi.todo.todospringboot.search.SearchContainer;
import com.learnindi.todo.todospringboot.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class about category
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("Redundant parameter: id must be null!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody Category category) {
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity<>("Missed parameter: id can't be null or zero!", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity<>("Missed parameter: title", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.updateCategory(category);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        Category foundCategory = categoryService.findCategoryById(id);
        if (foundCategory == null) {
            String msgError = String.format("Category with this id=%s not found", id);
            logger.error(msgError);
            return new ResponseEntity(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(foundCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            String msgError = String.format("Category with this id=%s not found", id);
            logger.error(msgError, ex);
            return new ResponseEntity<>(msgError, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> searchCategory(@RequestBody SearchContainer<Category> container) {
        return ResponseEntity.ok(categoryService.searchCategories(container));
    }
}
