package com.shinaz.bookstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    private Book book;


    @BeforeEach
    public void setUp() {
        book = new Book();
    }


    @Test
    public void bookTest(){
        final String result = book.toString();
        assertEquals("Book(bookId=null, title=null, authors=[], category=null, price=0.0, totalCount=0, sold=0)", result);
    }
}
