package com.shinaz.bookstore.controller;

import com.shinaz.bookstore.dto.BookReqDto;
import com.shinaz.bookstore.model.Book;
import com.shinaz.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/api")
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
    @GetMapping(path = "/book")
    public ResponseEntity<Book> getTodoById(@RequestParam(value = "id") Long id){
        return bookService.getBookById(id);
    }

    /**
     * HTTP GET ALL
     */
    @GetMapping(path = "/allBooks")
    public ResponseEntity<List<Book>> getAllTodolist(){
        return bookService.getAllBooks();
    }

    /**
     * HTTP POST NEW ONE
     */
    @PostMapping(path = "/book")
    public ResponseEntity<String> addNewBook(@Valid @RequestBody BookReqDto bookReqDto){
        return bookService.addNewBook(bookReqDto);
    }

    /**
     * HTTP PUT UPDATE
     */
    @PutMapping(path = "/book")
    public ResponseEntity<String> updateBook(@Valid @RequestBody Book book) {
        return bookService.updateBook(book);
    }

    /**
     * HTTP DELETE
     */
    @DeleteMapping(path = "/book")
    public ResponseEntity<String> deleteBook(@RequestParam(value = "id") Long id) {
        return bookService.deleteBook(id);
    }
}
