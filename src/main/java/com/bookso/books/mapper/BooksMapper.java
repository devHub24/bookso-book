package com.bookso.books.mapper;

import com.bookso.books.dto.BooksDto;
import com.bookso.books.dto.OrderInDto;
import com.bookso.books.dto.OrdersDto;
import com.bookso.books.entity.Books;
import com.bookso.books.entity.Orders;
import org.springframework.stereotype.Service;

/*
* @author: santhosh kumar
* @description: Mapper class to convert Books Dto and Entities
 */
@Service
public class BooksMapper {

    /*
    * @author: santhosh kumar
    * @description: Method to convert Books to BooksDto
    * @params: BooksDto class
    * @returns: Converted Books class
     */
    public BooksDto toBooksDto(Books books){
        return BooksDto.builder()
                .bookCode(books.getBookCode())
                .genre(books.getGenre())
                .title(books.getTitle())
                .category(books.getCategory())
                .author(books.getAuthor())
                .publisher(books.getPublisher())
                .rating(books.getRating())
                .rate(books.getRate())
                .build();
    }

    /*
    * @author: santhosh kumar
    * @description: Method to convert booksDto to Books
    * @params: BooksDto class to be converted
    * @returns: Books entity
     */
    public Books toBooks(BooksDto booksDto){
        return Books.builder()
                .bookCode(booksDto.getBookCode())
                .title(booksDto.getTitle())
                .category(booksDto.getCategory())
                .author(booksDto.getAuthor())
                .genre(booksDto.getGenre())
                .publisher(booksDto.getPublisher())
                .rating(booksDto.getRating())
                .rate(booksDto.getRate())
                .build();
    }


}
