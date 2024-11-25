package com.bookso.books.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/*
* @author: santhosh kumar
* @description: Book Entity Class
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Books extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(unique = true)
    private Long bookCode;
    private String title;
    private String category;
    private String genre;
    private String author;
    private String publisher;
    private double rating;
    private double rate;

}
