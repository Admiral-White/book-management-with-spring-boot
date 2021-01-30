package com.semicolon.model.service.book;

import com.semicolon.model.data.model.Book;
import com.semicolon.model.data.repository.BookRepository;
import com.semicolon.model.web.exceptions.BookDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {

        if (book == null){
            throw new NullPointerException("Book object cant be null");
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) throws BookDoesNotExistException {

        Book savedBook = bookRepository.findById(book.getId()).orElse(null);
        if (savedBook == null) throw new BookDoesNotExistException("Book with id:" + book.getId() + "does not exist");
        else{
            if(book.getTitle() != null){
                savedBook.setTitle(book.getTitle());
            }
            if(book.getIsbn() != null){
                savedBook.setIsbn(book.getIsbn());
            }
            if(book.getAuthor() != null){
                savedBook.setAuthor(book.getAuthor());
            }
        }
        return bookRepository.save(savedBook);
    }

    @Override
    public Book findBookById(Long id) throws BookDoesNotExistException {

        Book savedBook = bookRepository.findById(id).orElse(null);

        // check that book exist
        if(savedBook != null){
            return savedBook;
        }
        else{
            throw new BookDoesNotExistException("Book with the id: " +id+ "does not exist");
        }

    }

    @Override
    public List<Book> findAllBooks() {

        return bookRepository.findAll();
    }



    @Override
    public void deleteBookById(Long id) throws BookDoesNotExistException {

        try {
            bookRepository.deleteById(id);
            }catch(Exception ex){
            throw new BookDoesNotExistException("Book with id does not exist");
        }

    }
}
