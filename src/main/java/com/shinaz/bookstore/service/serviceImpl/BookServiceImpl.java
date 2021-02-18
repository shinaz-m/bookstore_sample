package com.shinaz.bookstore.service.serviceImpl;




import com.shinaz.bookstore.dto.AuthorDto;
import com.shinaz.bookstore.dto.BookReqDto;
import com.shinaz.bookstore.dto.BookResDto;
import com.shinaz.bookstore.exceptions.BookNotFoundException;
import com.shinaz.bookstore.exceptions.DuplicateResourceException;
import com.shinaz.bookstore.model.Author;
import com.shinaz.bookstore.model.Book;
import com.shinaz.bookstore.repository.BookRepository;
import com.shinaz.bookstore.service.BookService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Log4j2
@Service
public class BookServiceImpl implements BookService {
    private final ModelMapper modelMapper;
    private BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(ModelMapper modelMapper, BookRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }


    /**
     * HTTP POST NEW ONE
     * Register new book with new identifier into database
     * Save the book In Book Domain
     * Maintain the count in BookCount variable
     * @param bookReqDto
     * @return http.CREATED with the success message
     */
    public ResponseEntity<String> addNewBook(BookReqDto bookReqDto) {
        Optional<Book> bookById = bookRepository.findById(bookReqDto.getBookId());
        bookById.ifPresent(book -> {
            throw new DuplicateResourceException("Book with same id present");
        });
        log.info("No Duplicates found.");
        Book book = mapBookDtoToBook(bookReqDto);
        log.info( "The data are mapped and ready to save.");
        bookRepository.save(book);
        log.info("A new Book is been added" );
        return ResponseEntity.status(CREATED).body("Entity created");
    }



    /**
     * HTTP GET BY ID
     * retrieve book details through a particular identifier
     * get list of authors related to a book
     */
    public ResponseEntity<BookResDto> getBookById(Long index) {
        Book book=bookRepository.findById(index).orElseThrow(() ->  new BookNotFoundException(String.format("Book with id: %s is not found.", index)));
        log.info("Book fetched with Id :{}", index);
        BookResDto bookResDto=mapBookToBookDto(book);
        log.info( "The data are mapped to dto successfully");
        return ResponseEntity.status(OK).body(bookResDto);
    }

    /**
     * HTTP GET ALL
     */
    public ResponseEntity<List<BookResDto>> getAllBooks() {
        List<Book> books=bookRepository.findAll();
        log.info("All Books fetched" );
        List<BookResDto> bookResDtoList=mapBookListToBooKDtoList(books);
        log.info( "The data are mapped to dto successfully");
        return ResponseEntity.status(OK).body(bookResDtoList);
    }


    /**
     * HTTP PUT UPDATE
     */
    public ResponseEntity<String> updateBook(BookReqDto bookReqDto) {
        if(bookRepository.existsById(bookReqDto.getBookId())){
            Book book = mapBookDtoToBook(bookReqDto);
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


    /**
     * to convert BookDto class to Book model class
     * @param bookReqDto
     * @return Book class
     */
    private Book mapBookDtoToBook(BookReqDto bookReqDto){
        Book book = modelMapper.map(bookReqDto, Book.class);
        Set<Author> authorSet= new HashSet<>();
        for(AuthorDto authorDto :bookReqDto.getAuthors()){
            Author author=modelMapper.map(authorDto,Author.class);
            authorSet.add(author);
        }
        book.setAuthors(authorSet);
        return book;
    }

    /**
     * to convert Book class to Book response DTO class
     * @param book
     * @return Book response DTO
     */
    private BookResDto mapBookToBookDto(Book book) {
        BookResDto bookResDto=modelMapper.map(book, BookResDto.class);
        Set<AuthorDto> authorDTOs=new HashSet<>();
        for(Author author:book.getAuthors()){
            AuthorDto authorDto = new AuthorDto(author.getAuthorName());
            authorDTOs.add(authorDto);
        }
        bookResDto.setAuthors(authorDTOs);
        return bookResDto;
    }

    /**
     * to convert List of Book class to List of Book response DTO class
     * @param books
     * @return List of Book response DTO class
     */
    private List<BookResDto> mapBookListToBooKDtoList(List<Book> books) {
        List<BookResDto> bookResDtoList=new ArrayList<>();
        for(Book book:books){
            bookResDtoList.add(mapBookToBookDto(book));
        }
        return bookResDtoList;
    }

}
