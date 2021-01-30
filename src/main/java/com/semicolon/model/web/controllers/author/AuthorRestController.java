package com.semicolon.model.web.controllers.author;

import com.semicolon.model.data.model.Author;
import com.semicolon.model.service.author.AuthorService;
import com.semicolon.model.web.exceptions.AuthorDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author")
@Slf4j
public class AuthorRestController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<?> saveAuthor(@RequestBody Author author){

        // log request body
        log.info("Request object --> {}", author);

        // save request body
        try{
            authorService.saveAuthor(author);
        }catch (NullPointerException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<?> findAllAuthors(){

        log.info("Get endpoint called");

        List<Author> authorList = authorService.findAllAuthors();

        log.info("Retrieve authors from the database -->{}", authorList);
        return ResponseEntity.ok().body(authorList);
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<?> findOneAuthor(@PathVariable("id")Long id){

       log.info("Id of author to be found -->{}", id);

       Author author;
       try{
           author = authorService.findAuthorById(id);
       }catch(AuthorDoesNotExistException ex){

           return ResponseEntity.badRequest().body(ex.getMessage());

       }

       return ResponseEntity.ok().body(author);

    }


    @PatchMapping("/one")
    public ResponseEntity<?> updateAuthor(@RequestBody Author author){

        try{
            author = authorService.updateAuthor(author);
        }catch (AuthorDoesNotExistException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return ResponseEntity.ok().body(author);
    }

    @DeleteMapping("/one/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){

        log.info("id of author to be found --> {}", id);

        try{
            authorService.deleteAuthorById(id);
        }catch(AuthorDoesNotExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
