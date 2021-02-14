package com.shinaz.bookstore.service;

import com.shinaz.bookstore.Model.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Log4j2
@Service
public class BookService {

    /**
     * HTTP POST NEW ONE
     */
    public ResponseEntity<String> addNewBook(Book book) {
        return null;
    }


    /**
     * HTTP GET to get book by id
     */
    public ResponseEntity<Book> getBookById(Long index) {
        return null;
    }

    /**
     * HTTP GET ALL
     */
    public ResponseEntity<List<Book>> getAllBooks() {
        return null;
    }


    /**
     * HTTP PUT UPDATE
     */
    public ResponseEntity<String> updateBook(Book book) {
        return null;
    }

    /**
     * HTTP DELETE
     */
    public ResponseEntity<String> deleteBook(Long index) {
        return null;
    }
}
