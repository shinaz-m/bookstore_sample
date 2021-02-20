package com.shinaz.bookstore.service;

import com.shinaz.bookstore.dto.AuthorDto;
import com.shinaz.bookstore.dto.BookReqDto;
import com.shinaz.bookstore.dto.BookResDto;
import com.shinaz.bookstore.exceptions.BookNotFoundException;
import com.shinaz.bookstore.exceptions.DuplicateResourceException;
import com.shinaz.bookstore.model.Author;
import com.shinaz.bookstore.model.Book;
import com.shinaz.bookstore.model.Category;
import com.shinaz.bookstore.repository.BookRepository;
import com.shinaz.bookstore.service.serviceimpl.BookServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.modelmapper.ModelMapper;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@SpringBootTest
public class BookServiceTest {
    private MockMvc mockMvc;

    @Mock
    ModelMapper modelMapper=new ModelMapper();

    @Mock
    private BookRepository mockBookRepository;


    @InjectMocks
    private BookServiceImpl mockBookService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mockBookService)
                .build();
    }

    @Test
    public void addNewBookTest(){
        Set<Author> authorSet=new HashSet<>();
        authorSet.add(new Author(0,"author Name"));
        Set<AuthorDto> authorDtoSet=new HashSet<>();
        authorDtoSet.add(new AuthorDto("author Name"));
        Book book=new Book(5l,"title",  authorSet, Category.ACTION,9,5,2);
        Book existingBook=new Book(4l,"title",  authorSet, Category.ACTION,9,5,2);
        when(mockBookRepository.findById(4l)).thenReturn(Optional.of(existingBook));
        when(mockBookRepository.save(any(Book.class))).thenReturn(book);
        when(modelMapper.map(any(BookReqDto.class), eq(Book.class))).thenReturn(book);

        assertThrows(DuplicateResourceException.class, () -> mockBookService.addNewBook(new BookReqDto(4l,"title", authorDtoSet,Category.ACTION,9,5)));
        ResponseEntity<String> responseEntity=ResponseEntity.status(CREATED).body("Entity created");
        final ResponseEntity<String> result = mockBookService.addNewBook(new BookReqDto(5l,"title", authorDtoSet,Category.ACTION,9,5));
        assertEquals(responseEntity, result);

    }

    @Test
    public void getBookByIdTest(){
        Set<Author> authorSet=new HashSet<>();
        Author author= new Author(1,"author Name");
        authorSet.add(author);
        Set<AuthorDto> authorDtoSet=new HashSet<>();
        authorDtoSet.add(new AuthorDto("author Name"));
        Optional<Book> book= Optional.of(new Book(5L, "title", authorSet, Category.ACTION, 0, 5, 2));
        BookResDto bookResDto=new BookResDto(5l,"title", authorDtoSet,Category.ACTION,9,5,2);

        when(mockBookRepository.findById(anyLong())).thenReturn(book);
        when(mockBookRepository.findById(0L)).thenThrow( new BookNotFoundException( String.format("Invalid book id 0L")));
        when(modelMapper.map(any(Book.class), eq(BookResDto.class))).thenReturn(bookResDto);
        assertThrows(BookNotFoundException.class, () -> mockBookService.getBookById(0L));
        ResponseEntity<BookResDto> responseEntity=ResponseEntity.status(HttpStatus.OK).body(bookResDto);
        ResponseStatusException response= new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid book id 0L"));
        final ResponseEntity<BookResDto> result = mockBookService.getBookById(5L);
        assertEquals(responseEntity, result);
    }

    @Test
    public void getAllBookTest(){
        List<BookResDto> bookResDtoList=new ArrayList<>();
        Set<AuthorDto> authorDtoSet=new HashSet<>();
        authorDtoSet.add(new AuthorDto("author Name"));
        BookResDto bookResDto=new BookResDto(5l,"title", authorDtoSet,Category.ACTION,9,5,2);
        bookResDtoList.add(bookResDto);
        List<Book> bookList=new ArrayList<>();
        Set<Author> authorSet=new HashSet<>();
        Author author= new Author(1,"author Name");
        authorSet.add(author);
        bookList.add(new Book(5L, "title", authorSet, Category.ACTION, 0, 5, 2));

        when(modelMapper.map(any(Book.class), eq(BookResDto.class))).thenReturn(bookResDto);
        when(mockBookRepository.findAll()).thenReturn(bookList);
        ResponseEntity<List<BookResDto>> responseEntity=ResponseEntity.status(HttpStatus.OK).body(bookResDtoList);

        final ResponseEntity<List<BookResDto>> result = mockBookService.getAllBooks();
        assertEquals(responseEntity, result);
    }



    @Test
    public void updateBookTest(){
        Set<AuthorDto> authorDtoSet=new HashSet<>();
        authorDtoSet.add(new AuthorDto("author Name"));
        BookReqDto book=new BookReqDto(5l,"title",authorDtoSet , Category.ACTION,0,5);
        when(mockBookRepository.existsById(book.getBookId())).thenReturn(true);
        BookReqDto book1=new BookReqDto(1l,"title", authorDtoSet, Category.ACTION,0,5);
        when(mockBookRepository.existsById(book1.getBookId())).thenReturn(false);
        Set<Author> authorSet=new HashSet<>();
        Author author= new Author(1,"author Name");
        authorSet.add(author);
        when(modelMapper.map(any(BookReqDto.class), eq(Book.class))).thenReturn(new Book(5l,"title", authorSet, Category.ACTION,0,5,2));
        when(mockBookRepository.save(any(Book.class))).thenReturn(new Book(5l,"title", authorSet, Category.ACTION,0,5,2));
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
