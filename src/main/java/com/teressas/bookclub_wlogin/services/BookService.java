package com.teressas.bookclub_wlogin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teressas.bookclub_wlogin.models.Book;
import com.teressas.bookclub_wlogin.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepo;
	
	public List<Book> allBooks() {
		return bookRepo.findAll();
	}
	
	public Book saveBook(Book book) {
		return bookRepo.save(book);
	}
	
	public Book findOneBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	public void deleteOneBook(Long id) {
		bookRepo.deleteById(id);
		
	}
}
