package com.vermeg.ams.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vermeg.ams.bookstore.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
