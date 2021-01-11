package com.learnindi.todo.todospringboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class about task
 *
 */
@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Task {

    /**
     * ID
     */
    private Long id;

    /**
     * Title of task
     */
    private String title;

    /**
     * Completed task or not (values: 0, 1, null)
     */
    private Integer completed;

    /**
     * Task completion date
     */
    private LocalDateTime date;

    /**
     * Priority of task
     */
    private Priority priority;

    /**
     * Category of task
     */
    private Category category;

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
    @Column(name = "completed")
    public Integer getCompleted() {
        return completed;
    }

    @Basic
    @Column(name = "date")
    public LocalDateTime getDate() {
        return date;
    }

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    public Priority getPriority() {
        return priority;
    }


    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }

}
