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
    private String searchText;
}
