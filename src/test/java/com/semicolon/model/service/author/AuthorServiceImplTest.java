package com.semicolon.model.service.author;

import com.semicolon.model.data.model.Author;
import com.semicolon.model.data.repository.AuthorRepository;
import com.semicolon.model.web.exceptions.AuthorDoesNotExistException;
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
class AuthorServiceImplTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorService authorService = new AuthorServiceImpl();

    @Autowired
    AuthorService authorServiceImpl;

    Author testAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testAuthor = new Author();
    }

    @Test
    void mockTheSaveAuthorToRepository() {
        when(authorRepository.save(testAuthor)).thenReturn(testAuthor);
        authorService.saveAuthor(testAuthor);

        verify(authorRepository, times(1)).save(testAuthor);
    }

    @Test
    void mockTheFindAuthorByIdToRepository() throws AuthorDoesNotExistException {
        when(authorRepository.findById(21L)).thenReturn(Optional.of(testAuthor));
        authorService.findAuthorById(21L);

        verify(authorRepository, times(1)).findById(21L);


    }

    @Test
    void mockTheFindAllAuthorsToRepository() {

        List<Author> authorList = new ArrayList<>();
        when(authorRepository.findAll()).thenReturn(authorList);
        authorService.findAllAuthors();

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void mockTheDeleteAuthorByIdToRepositoryTest() throws AuthorDoesNotExistException {

        doNothing().when(authorRepository).deleteById(21L);
        authorService.deleteAuthorById(21L);

        verify(authorRepository, times(1)).deleteById(21L);

    }


}