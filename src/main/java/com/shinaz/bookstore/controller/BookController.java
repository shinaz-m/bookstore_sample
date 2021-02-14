package com.shinaz.bookstore.controller;

import com.shinaz.bookstore.Model.Book;
import com.shinaz.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class BookController {


    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * HTTP GET
     */
    @GetMapping(path = "/api/book")
    public ResponseEntity<Book> getTodoById(@RequestParam(value = "id") int id){
        return bookService.getBookById(id);
    }

    /**
     * HTTP GET ALL
     */
    @GetMapping(path = "/api/allBooks")
    public ResponseEntity<List<Book>> getAllTodolist(){
        return bookService.getAllBooks();
    }

    /**
     * HTTP POST NEW ONE
     */
    @PostMapping(path = "/api/book")
    public ResponseEntity<String> addNewBook(@Valid @RequestBody Book book){
        return bookService.addNewBook(book);
    }

    /**
     * HTTP PUT UPDATE
     */
    @PutMapping(path = "/api/book")
    public ResponseEntity<String> updateBook(@Valid @RequestBody Book book) {
        return bookService.updateBook(book);
    }

    /**
     * HTTP DELETE
     */
    @DeleteMapping(path = "/api/book")
    public ResponseEntity<String> deleteBook(@RequestParam(value = "id") int id) {
        return bookService.deleteBook(id);
    }
}
