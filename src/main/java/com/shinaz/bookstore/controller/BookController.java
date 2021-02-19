package com.shinaz.bookstore.controller;

import com.shinaz.bookstore.dto.BookReqDto;
import com.shinaz.bookstore.dto.BookResDto;
import com.shinaz.bookstore.model.Book;
import com.shinaz.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Book controller", description = "This book controller class deals with basic CRUD operation on Book.Class with Author details")
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
    @Operation(summary = "Get Book By Id", description = "To get the book details of book based on the unique Book Id number", tags = {"getBookById"})
    @GetMapping(path = "/book")
    public ResponseEntity<BookResDto> getBookById(@RequestParam(value = "id") Long id){
        return bookService.getBookById(id);
    }

    /**
     * HTTP GET ALL
     */
    @Operation(summary = "Get All book details", description = "To get the book details of all book in the book database", tags = {"getAllBooks"})
    @GetMapping(path = "/allBooks")
    public ResponseEntity<List<BookResDto>> getAllBooks(){
        return bookService.getAllBooks();
    }

    /**
     * HTTP POST NEW ONE
     */
    @Operation(summary = "post a new book data", description = "To add a book details of new book to book database", tags = {"addNewBook"})
    @PostMapping(path = "/book")
    public ResponseEntity<String> addNewBook(@Valid @RequestBody BookReqDto bookReqDto){
        return bookService.addNewBook(bookReqDto);
    }

    /**
     * HTTP PUT UPDATE
     */
    @Operation(summary = "update a existing book data", description = "To update a book details of existing book to book database", tags = {"updateBook"})
    @PutMapping(path = "/book")
    public ResponseEntity<String> updateBook(@Valid @RequestBody BookReqDto bookReqDto) {
        return bookService.updateBook(bookReqDto);
    }

    /**
     * HTTP DELETE
     */
    @Operation(summary = "Delete Book By Id", description = "To delete the book details of book based on the unique Book Id number", tags = {"deleteBook"})
    @DeleteMapping(path = "/book")
    public ResponseEntity<String> deleteBook(@RequestParam(value = "id") Long id) {
        return bookService.deleteBook(id);
    }
}
