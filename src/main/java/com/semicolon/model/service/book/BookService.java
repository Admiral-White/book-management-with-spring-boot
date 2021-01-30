package com.semicolon.model.service.book;

import com.semicolon.model.data.model.Book;
import com.semicolon.model.web.exceptions.BookDoesNotExistException;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    Book updateBook(Book book) throws BookDoesNotExistException;

    Book findBookById(Long id) throws BookDoesNotExistException;

    List<Book> findAllBooks();

    void deleteBookById(Long id) throws BookDoesNotExistException;
}
