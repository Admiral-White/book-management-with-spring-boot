package com.semicolon.model.service.author;

import com.semicolon.model.data.model.Author;
import com.semicolon.model.web.exceptions.AuthorDoesNotExistException;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(Author author);
    Author updateAuthor(Author author ) throws AuthorDoesNotExistException;

    Author findAuthorById(Long id) throws AuthorDoesNotExistException;

    List<Author> findAllAuthors();

    void deleteAuthorById(Long id) throws AuthorDoesNotExistException;

}
