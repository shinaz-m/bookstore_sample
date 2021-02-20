package com.shinaz.bookstore.dto;

import com.shinaz.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookResDtoTest {
    private BookResDto bookResDto;


    @BeforeEach
    public void setUp() {
        bookResDto = new BookResDto();
    }


    @Test
    public void bookTest(){
        final String result = bookResDto.toString();
        assertEquals("BookResDto(bookId=null, title=null, authors=null, category=null, price=0.0, totalCount=0, sold=0)", result);
    }
}
