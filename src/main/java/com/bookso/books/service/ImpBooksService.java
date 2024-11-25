package com.bookso.books.service;


import com.bookso.books.dto.BooksDto;
import com.bookso.books.entity.Books;
import com.bookso.books.enums.BooksErrors;
import com.bookso.books.exceptions.BooksoException;
import com.bookso.books.mapper.BooksMapper;
import com.bookso.books.repo.IBooksRepository;
import com.bookso.books.utils.BooksUtils;
import io.micrometer.core.annotation.Timed;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static com.bookso.books.constatns.BooksConstants.*;
import static com.bookso.books.enums.BooksErrors.BOOK_NOT_FOUND;
import static com.bookso.books.enums.BooksErrors.GENERIC_ERROR;

/*
* @author: santhosh kumar
* @description: Implementation class for BooksService
 */
@Slf4j
@Service
@Transactional
public class ImpBooksService implements IBooksService{

    @Autowired
    private BooksMapper booksMapper;
    @Autowired
    private IBooksRepository booksRepository;
    @Autowired
    private BooksUtils booksUtils;

    /*
    * @author: santhosh kumar
    * @description: Method to create a new Book
    * @params: BooksDto class for new Book
    * @returns: True or False
     */
    @Override
    @Timed(value = "books.create", description = "Method to create a book")
    public boolean createBooks(BooksDto booksDto) {
        try {
            Books books = booksMapper.toBooks(booksDto);
            books.setBookCode(generateBookCode());
            books.setGenre(getGenre(booksDto.getGenre()));
            books.setCategory(getCategory(booksDto.getCategory()));
            booksRepository.save(books);
            log.info("Book Saved successfully:- "+books);

            return true;
        }catch(DataIntegrityViolationException de){
            log.error("Book already exists");
            throw new BooksoException(BooksErrors.BOOK_ALREADY_EXISTS, BOOK_ALREADY_EXISTS);
        }catch(DataAccessException da){
            log.error("Error while Creating Book");
            throw new BooksoException(BooksErrors.GENERIC_ERROR, "Error while Creating Book");
        }
    }

    /*
    * @author: santhosh kumar
    * @description: Method to get a book by its code
    * @params: Book code
    * @returns: Book Entity
     */
    @Override
    @Timed(value = "books.get.code", description = "Method to get a book by code")
    public BooksDto getBooksByCode(Long code) {
        try{
            Books book = booksRepository.findByBookCode(code).orElseThrow(()->
                 new BooksoException(BOOK_NOT_FOUND,
                        String.format(BOOK_NOT_FOUND_CODE, String.valueOf(code)))
            );
            return booksMapper.toBooksDto(book);
        }catch (EmptyResultDataAccessException e){
            log.error("Book not found with the code: "+ code);
            throw new BooksoException(BOOK_NOT_FOUND,String.format(BOOK_NOT_FOUND_CODE,String.valueOf(code)));
        }

    }

    /*
    * @author: santhosh kumar
    * @description: Method to Fetch all the books
    * @params:
    * @returns: List of BooksDto
     */
    @Override
    @Timed(value = "books.get.all",description = "Method to get all books Traditional")
    public List<BooksDto> getAllBooks() {
        try {
            List<Books> booksList = booksRepository.findAll();
            List<BooksDto> booksDtoList = booksList.stream().map(books -> booksMapper.toBooksDto(books)).toList();
            log.info("List of books Fetched");
            return booksDtoList;
        }catch(DataAccessException a){
            log.error("Empty set of Data Fetched");
            throw new BooksoException(GENERIC_ERROR, "Error while fetching Books");
        }

    }

    /*
    * @author: santhosh kumar
    * @description: Method to Fetch Books By Genre
    * @params: Book Genre
    * @returns: List of Books
     */
    @Override
    @Transactional(rollbackOn = {BooksoException.class, DataAccessException.class, PersistenceException.class})
    @Timed(value = "books.fetch.genre", description = "Method to fetch books by genre")
    public List<BooksDto> getBooksByGenre(String genreCode) {
        try(Stream<Books> booksStream = booksRepository.fetchAllByGenre(getGenre(genreCode))){
            List<BooksDto> booksListDto = booksStream.map(book->booksMapper.toBooksDto(book)).toList();
            if(booksListDto.isEmpty()){
                throw new BooksoException(GENERIC_ERROR, NO_BOOKS_FOUND);
            }
            return booksListDto;

        }catch(DataAccessException | PersistenceException e){
            throw new BooksoException(GENERIC_ERROR, e.getMessage());
        }
    }

    /*
    * @author: santhosh kumar
    * @description: Method to get Book by Author
    * @params: Author
    * @returns: List of Books
     */
    @Override
    @Transactional(rollbackOn = {BooksoException.class, DataAccessException.class})
    @Timed(value = "books.fetch.author", description = "Method to fetch books by author")
    public List<BooksDto> getBooksByAuthor(String author) {

        try(Stream<Books> booksStream = booksRepository.fetchAllByAuthor(author)){

            List<BooksDto> booksListDto = booksStream.map(book -> booksMapper.toBooksDto(book)).toList();
            if(booksListDto.isEmpty()){
                throw new BooksoException(GENERIC_ERROR, NO_BOOKS_FOUND);
            }
            return booksListDto;

        }catch(DataAccessException e){
            throw new BooksoException(GENERIC_ERROR, e.getMessage());
        }
    }

    /*
    * @author: santhosh kumar
    * @description: Method to get all the books with specific publisher
    * @params: Publisher
    * @returns: List of BooksDto
     */
    @Override
    @Transactional(rollbackOn = {BooksoException.class, DataAccessException.class})
    @Timed(value = "books.get.publisher", description = "Method to fetch books by publisher")
    public List<BooksDto> getBooksByPublisher(String publisher) {
        try(Stream<Books> booksList = booksRepository.fetchAllByPublisher(publisher)){
            List<BooksDto> booksDtoList = booksList.map(book-> booksMapper.toBooksDto(book)).toList();
            if(booksDtoList.isEmpty()){
                throw new BooksoException(GENERIC_ERROR, NO_BOOKS_FOUND);
            }
            return booksDtoList;
        }catch (DataAccessException e){
            throw new BooksoException(GENERIC_ERROR, "Error while fetching Books");
        }
    }

    /*
    * @author: santhosh kumar
    * @description: Method to update Book
    * @params: Bookcode and BooksDto to be updated
    * @returns: String
     */
    @Override
    @Transactional(rollbackOn = {BooksoException.class, DataAccessException.class})
    @Timed(value = "books.update", description = "Method to update book")
    public boolean updateBooks(Long bookCode, BooksDto booksDto) {
        try{
            Books book = booksRepository.findByBookCode(bookCode).orElseThrow(()->
                new BooksoException(BOOK_NOT_FOUND, String.format(BOOK_NOT_FOUND_CODE,bookCode)));
            book.setCategory(getCategory(booksDto.getCategory()));
            book.setGenre(getGenre(booksDto.getGenre()));
            book.setAuthor(booksDto.getAuthor());
            book.setPublisher(booksDto.getPublisher());
            book.setRate(booksDto.getRate());
            book.setRating(booksDto.getRating());
            booksRepository.save(book);
            return true;
        }catch( DataAccessException e){
            throw  new BooksoException(GENERIC_ERROR,e.getMessage());
        }
    }

    /*
    * @author: santhosh kumar
    * @description: Method to delete a book by code
    * @params: Book code
    * @returns:
     */
    @Override
    public void deleteBooks(Long bookCode) {
        try{
            booksRepository.deleteByBookCode(bookCode);
        }catch(EmptyResultDataAccessException e){
            throw new BooksoException(BOOK_NOT_FOUND, String.format(BOOK_NOT_FOUND_CODE, bookCode));
        }
    }

    /*
    * @author: santhosh kumar
    * @description: to fetch all Books efficiently with stream
    * @params:
    * @returns: List of Books
     */
    @Override
    @Transactional(rollbackOn = {BooksoException.class, DataAccessException.class, PersistenceException.class})
    @Timed(value = "books.fetch.all",description = "Method to fetch all books")
    public List<BooksDto> fetchAll() {
        try(Stream<Books> booksStream = booksRepository.fetchAll()){

            List<Books> booksList = booksStream.toList();
            if(booksList.isEmpty()){
                throw new BooksoException(GENERIC_ERROR, NO_BOOKS_FOUND);
            }
            return booksList.stream().map(books -> booksMapper.toBooksDto(books)).toList();

        }catch (DataAccessException | PersistenceException e) {
            throw new BooksoException(GENERIC_ERROR, e.getMessage());
        }
    }

    /*
    * @author: santhosh kumar
    * @description: method to create a random BookCode
    * @params: nothing
    * @returns: randomly generated Alpha-Numerical value
     */
    private Long generateBookCode(){
        return 1000L+(new Random().nextInt(9000));
    }

    /*
    * @author: santhosh kumar
    * @description: Method to get the genre from genreMaps
    * @params: Genre Code
    * @returns: Genre of the Book
     */
    private String getGenre(String genreCode){
        return genreMaps.get(genreCode);
    }

    /*
    * @author: santhosh kumar
    * @description: Method to get category from category maps
    * @params: Category Code
    * @returns: Category of the book
     */
    private String getCategory(String categoryCode){
        return categoryMaps.get(categoryCode);
    }
}
