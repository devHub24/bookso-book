package com.bookso.books.repo;

import com.bookso.books.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface IBooksRepository extends JpaRepository<Books, Long> {

    public Optional<Books> findByBookCode(Long code);

    @Query("SELECT book FROM Books book")
    public Stream<Books> fetchAll();

    @Query("SELECT book FROM Books book WHERE book.genre = :genre")
    public Stream<Books> fetchAllByGenre(@Param("genre") String genre);

    @Query("SELECT book FROM Books book WHERE book.publisher LIKE %:publisher%")
    public Stream<Books> fetchAllByPublisher(@Param("publisher") String publisher);

    @Query("SELECT book FROM Books book WHERE book.author LIKE %:author%")
    public Stream<Books> fetchAllByAuthor(@Param("author") String author);

    public void deleteByBookCode(Long bookCode);
}
