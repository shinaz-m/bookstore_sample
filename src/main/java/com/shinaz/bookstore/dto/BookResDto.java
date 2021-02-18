package com.shinaz.bookstore.dto;

import com.shinaz.bookstore.model.Category;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookResDto {
    private Long bookId;
    private String title;
    private Set<AuthorDto> authors;
    @Enumerated(EnumType.STRING)
    private Category category;
    private float price;
    private int totalCount;
    private int sold;
}
