package com.learnindi.todo.todospringboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity class about priority
 *
 */
@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Priority {

    /**
     * ID
     */
    private Long id;

    /**
     * Title of priority
     */
    private String title;

    /**
     * Color of priority
     */
    private String color;

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
    @Column(name = "color")
    public String getColor() {
        return color;
    }
}
