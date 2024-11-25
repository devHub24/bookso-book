package com.bookso.books.utils;

import com.bookso.books.entity.Books;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class BooksUtils {

    public boolean isEmptyStream(Stream<Books> booksStream){
        return booksStream.toList().isEmpty();
    }
}
