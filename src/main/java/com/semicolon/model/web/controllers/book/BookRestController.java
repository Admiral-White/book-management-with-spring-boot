package com.semicolon.model.web.controllers.book;


import com.semicolon.model.data.model.Book;
import com.semicolon.model.service.book.BookService;
import com.semicolon.model.web.exceptions.BookDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookRestController {

    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> saveBook(@RequestBody Book book){

        // log request body
        log.info("Request object --> {}", book);

        //  save request body
        try {
            bookService.saveBook(book);

        }catch(NullPointerException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllBooks(){

        log.info("Get endpoint called");

        List<Book> bookList = bookService.findAllBooks();

        log.info("Retrieve books from database -->{}", bookList);

        return ResponseEntity.ok().body(bookList);
    }


    @GetMapping("/one/{id}")
    public ResponseEntity<?> findOneBook(@PathVariable("id") Long id){

        log.info("id of books to be found -->{}", id);

        Book book;

        try{
            book = bookService.findBookById(id);
        }catch(BookDoesNotExistException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok().body(book);
    }

    @PatchMapping("/one")
    public ResponseEntity<?> updateBook(@RequestBody Book book){

        try{
            book = bookService.updateBook(book);
        }catch (BookDoesNotExistException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping("/one/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){

        log.info("id of book to be found --> {}", id);

        try {
            bookService.deleteBookById(id);
            }catch(BookDoesNotExistException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.noContent().build();
    }
}


