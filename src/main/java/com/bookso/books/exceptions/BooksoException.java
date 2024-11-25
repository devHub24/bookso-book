package com.bookso.books.exceptions;


import com.bookso.books.enums.BooksErrors;
import com.bookso.books.enums.BooksoErrors;
import lombok.Data;


/*
* @author: santhosh kumar
* @description: Books Exception class
 */
@Data
public class BooksoException extends RuntimeException{

    private String message;
    private BooksoErrors error;

    public BooksoException(BooksoErrors error, String message){
        this.error = error;
        this.message = message;
    }
}
