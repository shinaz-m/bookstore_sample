package com.shinaz.bookstore.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representation of Author Table
 **/
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    /**
     * Unique Book Number given by company.
     * Eg: ISBN number
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    /**
     * Name of the author
     */
    private String authorName;



}
