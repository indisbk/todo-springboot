package com.learnindi.todo.todospringboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity class about category
 *
 */
@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Category {

    /**
     * ID
     */
    private Long id;

    /**
     * Title of category
     */
    private String title;

    /**
     * Completed tasks in the category
     */
    private Long completedCount;

    /**
     * Uncompleted tasks in the category
     */
    private Long uncompletedCount;

    // fill id by DB
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "completed_count")
    public Long getCompletedCount() {
        return completedCount;
    }

    @Basic
    @Column(name = "uncompleted_count")
    public Long getUncompletedCount() {
        return uncompletedCount;
    }

}
