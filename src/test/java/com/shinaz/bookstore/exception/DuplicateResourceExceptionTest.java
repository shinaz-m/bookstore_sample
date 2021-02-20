package com.shinaz.bookstore.exception;

import com.shinaz.bookstore.exceptions.BookNotFoundException;
import com.shinaz.bookstore.exceptions.DuplicateResourceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;

public class DuplicateResourceExceptionTest {
    @InjectMocks
    private DuplicateResourceException duplicateResourceException;

    @Before
    public void setUp() {
        duplicateResourceException= new DuplicateResourceException("message");
    }

    @Test
    public void BookNotFoundException() {
        assertEquals("message", duplicateResourceException.getMessage());
    }
}
