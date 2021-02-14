package com.shinaz.bookstore.model;

import com.shinaz.bookstore.Model.Book;
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
    public void authorTest(){
        final String result = book.toString();
        assertEquals("Book(bookId=null, title=null, author=null, category=null, price=0.0, totalCount=0, sold=0)", result);
    }
}
