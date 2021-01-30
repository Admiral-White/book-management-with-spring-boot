package com.semicolon.model.service.book;

import com.semicolon.model.data.model.Book;
import com.semicolon.model.data.repository.BookRepository;
import com.semicolon.model.web.exceptions.BookDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService = new BookServiceImpl();

    @Autowired
    BookService bookServiceImpl;

    Book testBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testBook = new Book();
    }

    @Test
    void mockTheSaveBookToRepository() {

       when(bookRepository.save(testBook)).thenReturn(testBook);
       bookService.saveBook(testBook);

       verify(bookRepository, times(1)).save(testBook);
    }

//    @Test
//    void mockTheUpdateBookRepositoryTest() throws BookDoesNotExistException {
//
//        when(bookRepository.save(testBook)).thenReturn(testBook);
//        bookService.saveBook(testBook);
//
//        Book book1 = new Book();
//        bookService.updateBook(book1);
//
//        verify(bookService, times(1)).updateBook(book1);


////        Book bookUpdate = new Book();
//        when(bookService.updateBook(testBook)).thenReturn(testBook);
////        bookService.saveBook(testBook);
//
////        verify(bookRepository, times(1)).save(testBook);
////        bookService.updateBook(testBook);
//        verify(bookService, times(1)).updateBook(testBook);

//    }

    @Test
    void mockTheFindBookByIdRepositoryTest() throws BookDoesNotExistException {
        
      when(bookRepository.findById(31L)).thenReturn(Optional.of(testBook));
      bookService.findBookById(31L);

      verify(bookRepository, times(1)).findById(31L);
        
    }

    @Test
    void mockTheFindAllBooksRepositoryTest() {

        List<Book> bookList = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(bookList);
        bookService.findAllBooks();

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void mockTheDeleteBookByIdRepositoryTest() throws BookDoesNotExistException {

        doNothing().when(bookRepository).deleteById(31L);
        bookService.deleteBookById(31L);

        verify(bookRepository, times(1)).deleteById(31L);



    }
}