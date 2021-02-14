package com.shinaz.bookstore.repository;

import com.shinaz.bookstore.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Long> {
}
