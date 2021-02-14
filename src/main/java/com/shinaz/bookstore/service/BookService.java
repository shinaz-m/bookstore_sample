package com.shinaz.bookstore.service;

import com.shinaz.bookstore.Model.Book;
import com.shinaz.bookstore.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Log4j2
@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * HTTP POST NEW ONE
     */
    public ResponseEntity<String> addNewBook(Book book) {
        bookRepository.save(book);
        log.info("A new Book is been added" );
        return ResponseEntity.status(CREATED).body("Entity created");
    }


    /**
     * HTTP GET to get book by id
     */
    public ResponseEntity<Book> getBookById(Long index) {
        log.info("TODO fetched with Id :{}", index);
        Book book=bookRepository.findById(index).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid book id %s", index)));
        return ResponseEntity.status(OK).body(book);
    }

    /**
     * HTTP GET ALL
     */
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("All Books fetched" );
        return ResponseEntity.status(OK).body(bookRepository.findAll());

    }


    /**
     * HTTP PUT UPDATE
     */
    public ResponseEntity<String> updateBook(Book book) {
        if(bookRepository.existsById(book.getBookId())){
            bookRepository.save(book);
            log.info("A Book with Id :{} been updated",book.getBookId() );
            return ResponseEntity.status(OK).body("Entity updated");
        } else {
            return ResponseEntity.status(UNPROCESSABLE_ENTITY).body("valid book index required for updating ");
        }
    }

    /**
     * HTTP DELETE
     */
    public ResponseEntity<String> deleteBook(Long index) {
        try {
            bookRepository.deleteById(index);
            log.info("Book with Id :{} is been deleted", index);
            return ResponseEntity.status(NO_CONTENT).body("Entity deleted");
        } catch (Exception e) {
            return ResponseEntity.status(UNPROCESSABLE_ENTITY).body("Entity deletion failed");
        }
    }
}
