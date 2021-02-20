package com.shinaz.bookstore.exception;

import com.shinaz.bookstore.exceptions.BookNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;

public class BookNotFoundExceptionTest {
    @InjectMocks
    private BookNotFoundException bookNotFoundException;

    @Before
    public void setUp() {
        bookNotFoundException = new BookNotFoundException("message");
    }

    @Test
    public void BookNotFoundException() {
        assertEquals("message", bookNotFoundException.getMessage());
    }
}
