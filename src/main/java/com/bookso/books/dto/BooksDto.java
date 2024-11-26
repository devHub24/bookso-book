package com.bookso.books.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "BooksDto", description = "Schema for BooksDto")
public class BooksDto {

    @Schema(description = "Book code", example="Random generated number")
    private Long bookCode;

    @Schema(description = "Book Title", example="The Hunt")
    @NotNull(message = "Title can't be null")
    private String title;

    @Schema(description = "Book Category", example="Fiction")
    @NotNull(message = "Category can't be null")
    private String category;

    @Schema(description = "Book Genre", example="Horror")
    @NotNull(message = "Genre can't be null")
    private String genre;

    @Schema(description = "Book Author", example="Anand")
    @NotNull(message = "Author can't be null")
    private String author;

    @Schema(description = "Book Publisher", example="Penguin")
    @NotNull(message = "Publisher can't be null")
    private String publisher;

    @Schema(description = "Book Rating", example="4.5")
    @NotNull(message = "Rating can't be null")
    private double rating;

    @Schema(description = "Book Fare", example="700")
    @NotNull(message = "Rate can't be null")
    private double rate;
}
