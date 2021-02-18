package com.shinaz.bookstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinaz.bookstore.dto.AuthorDto;
import com.shinaz.bookstore.dto.BookReqDto;
import com.shinaz.bookstore.dto.BookResDto;
import com.shinaz.bookstore.model.Category;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        Set<AuthorDto> authorDtoSet=new HashSet<>();
        AuthorDto authorDto= new AuthorDto("author Name");
        authorDtoSet.add(authorDto);
        BookReqDto book=new BookReqDto(0l,"title", authorDtoSet, Category.ACTION,0,5);

        when(mockBookService.addNewBook(any(BookReqDto.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Entity created"));
        MvcResult response =mockMvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andReturn();
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Set<AuthorDto> authorDtoSet=new HashSet<>();
        AuthorDto authorDto= new AuthorDto("author Name");
        authorDtoSet.add(authorDto);
        BookResDto book=new BookResDto(1L,"test title", authorDtoSet, Category.ACTION,0,5,2);
        ResponseEntity<BookResDto> responseEntity=ResponseEntity.status(HttpStatus.OK).body(book);
        when(mockBookService.getBookById(anyLong())).thenReturn(responseEntity);
        mockMvc.perform(get("/api/book?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.bookId").value(1L))
                .andExpect(jsonPath("$.title").value("test title"))
                .andExpect(jsonPath("$.category").value("ACTION"))
                .andExpect(jsonPath("$.price").value(0))
                .andExpect(jsonPath("$.totalCount").value(5))
                .andExpect(jsonPath("$.sold").value(2));

    }


    @Test
    public void getAllBooksTest() throws Exception {
        List<BookResDto> books=new ArrayList<>();
        when(mockBookService.getAllBooks()).thenReturn(ResponseEntity.ok(books));
        MvcResult response =mockMvc.perform(get("/api/allBooks"))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
    }




    @Test
    public void updateBookTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<AuthorDto> authorDtoSet=new HashSet<>();
        AuthorDto authorDto= new AuthorDto("author Name");
        authorDtoSet.add(authorDto);
        BookReqDto book=new BookReqDto(0L,"test title",  authorDtoSet, Category.ACTION,0,5);
        when(mockBookService.updateBook(any(BookReqDto.class))).thenReturn(ResponseEntity.ok("Entity updated"));
        MvcResult response =mockMvc.perform(put("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andReturn();
        assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
    }

    @Test
    public void deleteBookTest() throws Exception {
        ResponseEntity<String> responseEntity=ResponseEntity.status(HttpStatus.NO_CONTENT).body("valid book index required for updating ");
        when(mockBookService.deleteBook(anyLong())).thenReturn(responseEntity);
        MvcResult response =mockMvc.perform(delete("/api/book?id=5"))
                .andReturn();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getResponse().getStatus());
    }

}