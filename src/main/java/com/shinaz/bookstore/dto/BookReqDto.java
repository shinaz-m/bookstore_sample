package com.shinaz.bookstore.dto;

import com.shinaz.bookstore.model.Author;
import com.shinaz.bookstore.model.Category;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookReqDto {
    private Long bookId;
    private String title;
    private Set<AuthorReqDto> authors;
    @Enumerated(EnumType.STRING)
    private Category category;
    private float price;
    private int totalCount;

}
