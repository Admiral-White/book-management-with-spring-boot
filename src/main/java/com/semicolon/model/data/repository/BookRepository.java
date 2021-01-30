package com.semicolon.model.data.repository;

import com.semicolon.model.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
