package com.shinaz.bookstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinaz.bookstore.Model.Author;
import com.shinaz.bookstore.Model.Book;
import com.shinaz.bookstore.Model.Category;
import com.shinaz.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("Test For Book Controller")
public class BookControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BookService mockBookService;

    @InjectMocks
    private BookController mockBookController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockBookController)
                .build();
    }

    @Test
    public void addNewBookTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Author author= new Author(0,"author Name");
        Book book=new Book(0l,"title",author, Category.ACTION,0,5,2);

        when(mockBookService.addNewBook(any(Book.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Entity created"));
        MvcResult response =mockMvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Author author= new Author(0,"author Name");
        Book book=new Book(0L,"test title",author, Category.ACTION,0,5,2);
        ResponseEntity<Book> responseEntity=ResponseEntity.status(HttpStatus.OK).body(book);
        when(mockBookService.getBookById(anyInt())).thenReturn(responseEntity);
        mockMvc.perform(get("/api/todolist?id=5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.bookId").value(0L))
                .andExpect(jsonPath("$.title").value("test title"))
                .andExpect(jsonPath("$.author").value(author))
                .andExpect(jsonPath("$.category").value(Category.ACTION))
                .andExpect(jsonPath("$.price").value(0))
                .andExpect(jsonPath("$.totalCount").value(5))
                .andExpect(jsonPath("$.sold").value(2));

    }


    @Test
    public void getAllBooksTest() throws Exception {
        List<Book> books=new ArrayList<>();
        when(mockBookService.getAllBooks()).thenReturn(ResponseEntity.ok(books));
        MvcResult response =mockMvc.perform(get("/api/allBooks"))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
    }




    @Test
    public void updateBookTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Author author= new Author(0,"author Name");
        Book book=new Book(0L,"test title",author, Category.ACTION,0,5,2);
        when(mockBookService.updateBook(any(Book.class))).thenReturn(ResponseEntity.ok("Entity updated"));
        MvcResult response =mockMvc.perform(put("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
    }

    @Test
    public void deleteBookTest() throws Exception {
        ResponseEntity<String> responseEntity=ResponseEntity.status(HttpStatus.NO_CONTENT).body("valid book index required for updating ");
        when(mockBookService.deleteTodoItem(anyInt())).thenReturn(responseEntity);
        MvcResult response =mockMvc.perform(delete("/api/book?id=5"))
                .andReturn();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getResponse().getStatus());
    }

}