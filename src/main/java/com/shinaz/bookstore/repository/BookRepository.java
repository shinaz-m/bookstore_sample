package com.shinaz.bookstore.repository;

import com.shinaz.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Long> {
}
