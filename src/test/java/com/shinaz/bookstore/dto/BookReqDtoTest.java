package com.shinaz.bookstore.dto;

import com.shinaz.bookstore.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookReqDtoTest {
    private BookReqDto bookReqDto;

    @BeforeEach
    public void setUp() {
        bookReqDto = new BookReqDto();
    }



    @Test
    public void BookReqDtoTest(){
        final String result = bookReqDto.toString();
        assertEquals("BookReqDto(bookId=null, title=null, authors=null, category=null, price=0.0, totalCount=0)", result);
    }
}
