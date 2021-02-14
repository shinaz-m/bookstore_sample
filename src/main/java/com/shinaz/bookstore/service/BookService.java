package com.shinaz.bookstore.service;

import com.shinaz.bookstore.Model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    public ResponseEntity<Book> getBookById(int index) {
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
    public ResponseEntity<String> deleteTodoItem(int index) {
        return null;
    }
}
