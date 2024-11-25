package com.bookso.books.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/*
* @author: santhosh kumar
* @description: Dto class for Book Entity
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BooksDto {

    private Long bookCode;
    @NotNull(message = "Title can't be null")
    private String title;
    @NotNull(message = "Category can't be null")
    private String category;
    @NotNull(message = "Genre can't be null")
    private String genre;
    @NotNull(message = "Author can't be null")
    private String author;
    @NotNull(message = "Publisher can't be null")
    private String publisher;
    @NotNull(message = "Rating can't be null")
    private double rating;
    @NotNull(message = "Rate can't be null")
    private double rate;
}
