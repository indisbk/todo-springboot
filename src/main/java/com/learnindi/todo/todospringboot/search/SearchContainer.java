package com.learnindi.todo.todospringboot.search;

import lombok.*;

/**
 * Container for searching parameters
 *
 * @param <T>   type of data searching
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SearchContainer<T> {

    //---------------fields for searching---------------
    /**
     * Text for searching in title(for example) of object
     */
    private String searchText;

    /**
     * Task status (true/false/null)
     */
    private boolean taskStatus;

    /**
     * Category ID
     */
    private Long categoryId;

    /**
     * Priority ID
     */
    private Long priorityId;

    //---------------fields for paging---------------
    private Integer pageNumber;
    private Integer pageSize;

    //---------------fields for sorting---------------
    /**
     * Name of column for sorting
     */
    private String sortColumnName;

    /**
     * Ascending or descending
     */
    private String sortDirection;
}
