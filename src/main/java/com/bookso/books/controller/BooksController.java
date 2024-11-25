package com.bookso.books.controller;


import com.bookso.books.dto.BaseResponseDto;
import com.bookso.books.dto.BooksDto;
import com.bookso.books.service.IBooksService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

import static com.bookso.books.constatns.BooksConstants.*;
import static com.bookso.books.constatns.StatusConstants.STATUS_200;
import static com.bookso.books.constatns.StatusConstants.STATUS_201;

@RestController
@RequestMapping("/bookso/v1")
public class BooksController {

    @Autowired
    private IBooksService booksService;

    @PostMapping("/books")
    public ResponseEntity<BaseResponseDto> newBook(@NotNull @RequestBody BooksDto booksDto){
        booksService.createBooks(booksDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponseDto
                        .builder()
                        .code(STATUS_201)
                        .message(BOOK_CREATED_SC).build());
    }

    @GetMapping("/books/{code}")
    public ResponseEntity<BooksDto> getBookByCode(@NotNull @PathVariable("code") Long code){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksByCode(code));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BooksDto>> getAllBooks(){
        //return ResponseEntity.status(HttpStatus.OK).body(booksService.getAllBooks());
        return ResponseEntity.status(HttpStatus.OK).body(booksService.fetchAll());
    }

    @GetMapping("/books/{genreCode}/by-genre")
    public ResponseEntity<List<BooksDto>> getBooksByGenre(@NotNull @PathVariable("genreCode") String genreCode){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksByGenre(genreCode));
    }

    @GetMapping("/books/{author}/by-author")
    public ResponseEntity<List<BooksDto>> getBooksByAuthor(@NotNull @PathVariable("author") String author){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksByAuthor(author));
    }

    @GetMapping("/books/{publisher}/by-publisher")
    public ResponseEntity<List<BooksDto>> getBooksByPublisher(@NotNull @PathVariable("publisher") String publisher){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksByPublisher(publisher));
    }

    @PutMapping("/books/{bookCode}")
    public ResponseEntity<BaseResponseDto> updateBooks(@NotNull @PathVariable("bookCode") Long bookCode,
                                                       @RequestBody BooksDto booksDto){
        booksService.updateBooks(bookCode, booksDto);
        return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseDto.builder().code(STATUS_200).message(BOOK_UPDATED_SC).build());
    }

    @DeleteMapping("/books/{bookCode}")
    public ResponseEntity<BaseResponseDto> deleteBook(@NotNull @PathVariable("bookCode") Long bookCode){
        booksService.deleteBooks(bookCode);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseDto.builder().code(STATUS_200).message(BOOK_DELETED_SC).build());
    }

}
