package com.semicolon.model.data.repository;

import com.semicolon.model.data.model.Author;
import com.semicolon.model.data.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class BookRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    Book book = new Book();

    Author author = new Author();

    @BeforeEach
    void setUp() {


    }

    @Test
    public void whenISaveABook(){
       book.setTitle("The Night Hawk");
       book.setIsbn("091-645-772");
       author.setName("Admiral White");
       author.setAge(33);
       author.setGenre("Motivational");
       author = authorRepository.save(author);
       book.setAuthor(author);

       log.info("this is a log of information before being saved -->{}", book);

       book = bookRepository.save(book);

       log.info("This is the log of information after being saved -->{}", book);

       assertThat(book.getId()).isNotNull();

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIRetrieveAllBooks_thenReturnAListOfAllTheBooks(){
        // find books
        List<Book> books = bookRepository.findAll();

        log.info("get all the books from the database -->{}", books);

        //  assert that books is not empty
        assertThat(books).isNotEmpty();

        //  assert that the size of the table is the same on the database
        assertThat(books.size()).isEqualTo(2);

    }


    @Test
    public void whenIUpdateBook_thenReturnTheUpdate(){
        book.setTitle("The Prince of Persia");
        book.setIsbn("015-645-732");
        author.setGenre("History");
        author.setAge(79);
        author.setName("Briska Fox");
        author = authorRepository.save(author);
        book.setAuthor(author);

        book = bookRepository.save(book);

        log.info("This is the information after being saved -->{}", book);

        // create an update
        book.setTitle("The prince of Persia and Sodom");

        // save update
        book = bookRepository.save(book);

        // log after the update
        log.info("this is the log after the update -->{}", book);

        assertThat(book.getTitle()).isEqualTo("The prince of Persia and Sodom");
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIDeleteBook_thenBookShouldBeDeletedFromDatabase(){

        log.info("fetching all the data before the operation -->");

        List<Book> books = bookRepository.findAll();

        for (Book book : books ){
            System.out.println(book);
        }

        //  check if book exist
        boolean result = bookRepository.existsById(37L);
        log.info("result -->{}", result);

        // assert that book exist
        assertThat(result).isTrue();

        // delete book
        bookRepository.deleteById(37L);

        // log of book info after the deleting
        result = bookRepository.existsById(37L);
        log.info("result after the delete process -->{}",result);


        //  assert that book is still there on the database
        assertThat(result).isFalse();

    }

}