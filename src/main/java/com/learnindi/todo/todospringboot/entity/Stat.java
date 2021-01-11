package com.learnindi.todo.todospringboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity class about statistics
 *
 */
@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Stat {

    /**
     * ID
     */
    private Long id;

    /**
     * Total completed tasks
     */
    private Long completedTotal;

    /**
     * Total uncompleted tasks
     */
    private Long uncompletedTotal;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "completed_total")
    public Long getCompletedTotal() {
        return completedTotal;
    }

    @Basic
    @Column(name = "uncompleted_total")
    public Long getUncompletedTotal() {
        return uncompletedTotal;
    }

}
