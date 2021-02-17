package com.shinaz.bookstore.dto;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthorReqDto {
    private String authorName;
}
