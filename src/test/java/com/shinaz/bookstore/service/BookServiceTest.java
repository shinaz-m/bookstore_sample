package com.shinaz.bookstore.service;

import com.shinaz.bookstore.Model.Author;
import com.shinaz.bookstore.Model.Book;
import com.shinaz.bookstore.Model.Category;
import com.shinaz.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class BookServiceTest {

    @Mock
    private BookRepository mockBookRepository;

    @InjectMocks
    private BookService mockBookService;



    @Test
    public void addNewBookTest(){
        Author author= new Author(0,"author Name");
        Book book=new Book(0l,"title",author, Category.ACTION,0,5,2);
        when(mockBookRepository.save(any(Book.class))).thenReturn(book);
        ResponseEntity<String> responseEntity=ResponseEntity.status(CREATED).body("Entity created");

        final ResponseEntity<String> result = mockBookService.addNewBook(book);
        assertEquals(responseEntity, result);

    }

    @Test
    public void getBookByIdTest(){
        Author author= new Author(0,"author Name");
        Optional<Book> book= Optional.of(new Book(0L, "title", author, Category.ACTION, 0, 5, 2));
        when(mockBookRepository.findById(5L)).thenReturn(book);
        when(mockBookRepository.findById(0L)).thenThrow( new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid book id 0L")));
        Exception exception = assertThrows(ResponseStatusException.class, () ->
                mockBookService.getBookById(0L));
        ResponseEntity<Book> responseEntity=ResponseEntity.status(HttpStatus.OK).body(book.get());
        ResponseStatusException response= new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid book id 0L"));

        final ResponseEntity<Book> result = mockBookService.getBookById(5L);

        assertEquals(responseEntity, result);
    }

    @Test
    public void getAllBookTest(){
        List<Book> bookList=new ArrayList<>();
        when(mockBookRepository.findAll()).thenReturn(bookList);
        ResponseEntity<List<Book>> responseEntity=ResponseEntity.status(HttpStatus.OK).body(bookList);

        final ResponseEntity<List<Book>> result = mockBookService.getAllBooks();
        assertEquals(responseEntity, result);
    }



    @Test
    public void updateBookTest(){
        Author author= new Author(0,"author Name");
        Book book=new Book(0l,"title",author, Category.ACTION,0,5,2);
        when(mockBookRepository.existsById(book.getBookId())).thenReturn(true);
        Book book1=new Book(1l,"title",author, Category.ACTION,0,5,2);
        when(mockBookRepository.existsById(book.getBookId())).thenReturn(false);

        when(mockBookRepository.save(any(Book.class))).thenReturn(book);
        ResponseEntity<String> responseEntity=ResponseEntity.status(HttpStatus.OK).body("Entity updated");
        ResponseEntity<String> responseEntity2=ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("valid book index required for updating ");

        final ResponseEntity<String> result = mockBookService.updateBook(book);
        final ResponseEntity<String> result2 = mockBookService.updateBook(book1);

        assertEquals(responseEntity, result);
        assertEquals(responseEntity2, result2);

    }


    @Test
    public void deleteTodoItemTest(){
        ResponseEntity<String> responseEntity=ResponseEntity.status(HttpStatus.NO_CONTENT).body("Entity deleted");
        doNothing().when(mockBookRepository).deleteById(5L);
        final ResponseEntity<String> result = mockBookService.deleteBook(5L);
        verify(mockBookRepository,times(1)).deleteById(5L);
        assertEquals(responseEntity, result);

        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid book id 0"))).when(mockBookRepository).deleteById(6L);
        ResponseEntity<String> responseEntity1=ResponseEntity.status(UNPROCESSABLE_ENTITY).body("Entity deletion failed");
        final ResponseEntity<String> result1 = mockBookService.deleteBook(6L);
        assertEquals(responseEntity1, result1);
    }
}
