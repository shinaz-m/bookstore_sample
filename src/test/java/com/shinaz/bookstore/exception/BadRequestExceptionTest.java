package com.shinaz.bookstore.exception;

import com.shinaz.bookstore.exceptions.BadRequestException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;

public class BadRequestExceptionTest {

        @InjectMocks
        private BadRequestException badRequestException;

        @Before
        public void setUp() {
            badRequestException = new BadRequestException("message");
        }

        @Test
        public void BadRequestException() {
            assertEquals("message", badRequestException.getMessage());

        }
    }

