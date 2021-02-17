package com.shinaz.bookstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
    }


    @Test
    public void authorTest(){
        final String result = author.toString();
        assertEquals("Author(authorId=0, authorName=null)", result);
    }
}
