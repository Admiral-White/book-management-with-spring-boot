package com.semicolon.model.service.author;

import com.semicolon.model.data.model.Author;
import com.semicolon.model.data.repository.AuthorRepository;
import com.semicolon.model.web.exceptions.AuthorDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(Author author) {

        if(author == null){
            throw new NullPointerException("Author object can't be null");
        }


        return authorRepository.save(author);

    }

    @Override
    public Author updateAuthor(Author author) throws AuthorDoesNotExistException {

        Author savedAuthor = authorRepository.findById(author.getId()).orElse(null);
        if(savedAuthor == null){
            throw new AuthorDoesNotExistException("Author with id:" + author.getId() +" does not exist");
        }
        else{
            if (author.getName() != null){
                savedAuthor.setName(author.getName());
            }
            if(author.getGenre() != null){
                savedAuthor.setGenre(author.getGenre());
            }
            return authorRepository.save(savedAuthor);


        }

    }

    @Override
    public Author findAuthorById(Long id) throws AuthorDoesNotExistException {

        Author savedAuthor = authorRepository.findById(id).orElse(null);

        // check that author exist
        if(savedAuthor != null){
            return savedAuthor;
        }
        else{
            throw new AuthorDoesNotExistException("Author with the Id:"+id+"Does not "+"Exist");
            }

    }

    @Override
    public List<Author> findAllAuthors() {

        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthorById(Long id) throws AuthorDoesNotExistException {

        try{
            authorRepository.deleteById(id);
        }catch(Exception exception){
            throw new AuthorDoesNotExistException("Author with id does not exist ");
        }

    }
}
