package com.teressas.bookclub_wlogin.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teressas.bookclub_wlogin.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findAll();
}
