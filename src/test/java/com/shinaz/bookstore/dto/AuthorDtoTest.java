package com.shinaz.bookstore.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorDtoTest {

    private AuthorDto authorDto;

    @BeforeEach
    public void setUp() {
        authorDto = new AuthorDto();
    }


    @Test
    public void authorTest(){
        final String result = authorDto.toString();
        assertEquals("AuthorDto(authorName=null)", result);
    }
}
