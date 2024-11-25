package com.bookso.books.service;

import com.bookso.books.dto.BooksDto;

import java.util.List;

/*
* @author: santhosh kumar
* @description: Books Service interface
 */
public interface IBooksService {

    public boolean createBooks(BooksDto booksDto);
    public BooksDto getBooksByCode(Long code);
    public List<BooksDto> getAllBooks();
    public List<BooksDto> getBooksByGenre(String genre);
    public List<BooksDto> getBooksByAuthor(String author);
    public List<BooksDto> getBooksByPublisher(String publisher);
    public boolean updateBooks(Long bookCode, BooksDto booksDto);
    public void deleteBooks(Long bookCode);

    //Streaming
    public List<BooksDto> fetchAll();
}
