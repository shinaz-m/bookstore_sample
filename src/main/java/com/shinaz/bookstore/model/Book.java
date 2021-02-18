package com.shinaz.bookstore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Representation of Book Table
 **/
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    /**
     * Unique Book Number given by company.
     */
    @Id
    private Long bookId;

    /**
     * title of the book
     */
    @NotNull
    private String title;

    /**
     * author of the book
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();

    /**
     * category of the book
     * Eg: Novel, Fiction, etc
     */
    private Category category;

    /**
     * price of the book
     */
    @Min(value = 0, message = "Price should be positive value.")
    private float price;

    /**
     * Amount of book available
     */
    @Min(value = 0, message = "Total Count should be positive value.")
    private int totalCount;

    /**
     * Total copies of book sold
     */
    @Min(value = 0, message = "Total sell should be positive value.")
    private int sold;
}
