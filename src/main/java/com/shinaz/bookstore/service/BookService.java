package com.shinaz.bookstore.service;

import com.shinaz.bookstore.dto.BookReqDto;
import com.shinaz.bookstore.dto.BookResDto;
import org.springframework.http.ResponseEntity;


import java.util.*;




public interface BookService {

    ResponseEntity<String> addNewBook(BookReqDto bookReqDto);

    ResponseEntity<BookResDto> getBookById(Long index);

    ResponseEntity<List<BookResDto>> getAllBooks();

    ResponseEntity<String> updateBook(BookReqDto bookReqDto);

    ResponseEntity<String> deleteBook(Long index);

}
