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

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    Book book = new Book();

    Author author = new Author();

    @BeforeEach
    void setUp() {
    }

    // Test that we can save author to database (create)
    @Test
    public void whenISaveAuthor(){

        // create an instance of author
        author.setName("Michael Inyang");
        author.setAge(40);
        author.setGenre("words of wisdom");
        author.setId(11L);

        log.info("author instance before saving -->{}", author);
        author = authorRepository.save(author);

//        log.info("author instance after saving -->{}", author);

        assertThat(author.getId()).isNotNull();
        log.info("author instance after saving -->{}", author);



    }

    @Test  // Test that we can update
    public void whenIUpdateAuthor(){
        author.setName("Peter Books");
        author.setGenre("Spiritual");
        author.setAge(90);
        book.setAuthor(author);
        book.setTitle("the power of fasting");
        book.setIsbn("123-665-090");
        author.addBook(book);
        author = authorRepository.save(author);

        log.info("log after saving the author info -->{}", author);

        author.setName("Peter Diamond Books");
        author = authorRepository.save(author);

        log.info("this is the new information after running the update -->{}", author);

        assertThat(author.getName()).isEqualTo("Peter Diamond Books");


    }

    @Test  //  Test to see that we can delete
    @Transactional  // used when fetching from database and fetch type is .lazy
    @Rollback(value = false)  // this annotation is used to keep the operation specific on the data in question
    public void whenIDeleteAuthorFromDatabase_thenAuthorIsDeleted(){
        log.info("fetching all the data before this operation --> ");
        List<Author> allAuthors = authorRepository.findAll();
        for (Author author: allAuthors){
            System.out.println(author);
        }

        // check if author exists
        boolean result = authorRepository.existsById(23L);
        log.info("Result --> {}", result);

        // assert that the author exist
        assertThat(result).isTrue();

        // delete author
        authorRepository.deleteById(23L);

        result = authorRepository.existsById(23L);
        log.info("Result after delete--> {}", result);

        // check if author exists
        assertThat(result).isFalse();
    }

    @Test  // Test that we can retrieve or read
    @Transactional
    @Rollback(value = false)
    public void whenFindAllAuthorIsCalled_thenReturnAllAuthors(){
        // find authors
        List<Author> authors = authorRepository.findAll();

        log.info("get all the authors from the database -->{}", authors);

        // assert that author is not empty
        assertThat(authors).isNotEmpty();

        // assert that size of the table is the same on the database
        assertThat(authors.size()).isEqualTo(2);
    }
}