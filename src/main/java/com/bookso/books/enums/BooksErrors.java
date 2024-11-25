package com.bookso.books.enums;

import com.bookso.books.service.IBooksService;

/*
* @author: santhosh kumar
* @description: Errors of Books MS
 */
public enum BooksErrors implements BooksoErrors {

    BOOK_NOT_FOUND,
    BOOK_ALREADY_EXISTS,
    GENERIC_ERROR
}
