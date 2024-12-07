package com.bookso.books.controller;


import com.bookso.books.dto.BaseResponseDto;
import com.bookso.books.dto.BooksDto;
import com.bookso.books.dto.BooksRecord;
import com.bookso.books.dto.ErrorDto;
import com.bookso.books.service.IBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

import static com.bookso.books.constatns.BooksConstants.*;
import static com.bookso.books.constatns.StatusConstants.*;

@RestController
@RequestMapping("/bookso/v1")
@Tag(name = "BooksMS", description = "Books Application MS")
public class BooksController {

    @Autowired
    private IBooksService booksService;

    @Operation(summary = "CREATE Book", description = "API to create a new Book")
    @ApiResponse(responseCode = STATUS_201, content = @Content(schema = @Schema(implementation = BaseResponseDto.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping("/books")
    public ResponseEntity<BaseResponseDto> newBook(@NotNull @RequestBody BooksDto booksDto){
        booksService.createBooks(booksDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponseDto
                        .builder()
                        .code(STATUS_201)
                        .message(BOOK_CREATED_SC).build());
    }

    @Operation(summary = "FETCH Book By Code", description = "API to fetch book by code")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = BaseResponseDto.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/books/{code}")
    public ResponseEntity<BooksDto> getBookByCode(@NotNull @PathVariable("code") Long code){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksByCode(code));
    }

    @Operation(summary = "FETCH Books", description = "API to fetch all the books")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = List.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/books")
    public ResponseEntity<List<BooksDto>> getAllBooks(){
        //return ResponseEntity.status(HttpStatus.OK).body(booksService.getAllBooks());
        return ResponseEntity.status(HttpStatus.OK).body(booksService.fetchAll());
    }

    @Operation(summary = "Fetch Book by Genre", description = "API to Fetch books by Genre")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = List.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/books/{genreCode}/by-genre")
    public ResponseEntity<List<BooksDto>> getBooksByGenre(@NotNull @PathVariable("genreCode") String genreCode){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksByGenre(genreCode));
    }

    @Operation(summary = "Fetch Book by author", description = "API to Fetch books by author name")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = List.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/books/{author}/by-author")
    public ResponseEntity<List<BooksDto>> getBooksByAuthor(@NotNull @PathVariable("author") String author){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksByAuthor(author));
    }

    @Operation(summary = "Fetch Book by publisher", description = "API to Fetch books by Publisher name")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = List.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/books/{publisher}/by-publisher")
    public ResponseEntity<List<BooksDto>> getBooksByPublisher(@NotNull @PathVariable("publisher") String publisher){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksByPublisher(publisher));
    }

    @Operation(summary = "Update Book", description = "API to Update book")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = BaseResponseDto.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PutMapping("/books/{bookCode}")
    public ResponseEntity<BaseResponseDto> updateBooks(@NotNull @PathVariable("bookCode") Long bookCode,
                                                       @RequestBody BooksDto booksDto){
        booksService.updateBooks(bookCode, booksDto);
        return ResponseEntity.status(HttpStatus.OK)
                    .body(BaseResponseDto.builder().code(STATUS_200).message(BOOK_UPDATED_SC).build());
    }

    @Operation(summary = "Delete Book by code", description = "API to delete book by code")
    @ApiResponse(responseCode = STATUS_200, content = @Content(schema = @Schema(implementation = BaseResponseDto.class)))
    @ApiResponse(responseCode = STATUS_417, content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/books/{bookCode}")
    public ResponseEntity<BaseResponseDto> deleteBook(@NotNull @PathVariable("bookCode") Long bookCode){
        booksService.deleteBooks(bookCode);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponseDto.builder().code(STATUS_200).message(BOOK_DELETED_SC).build());
    }

    @Autowired
    private BooksRecord booksRecord;
    @GetMapping("/get-info")
    public BooksRecord getRecord(){
        return booksRecord;
    }

}
