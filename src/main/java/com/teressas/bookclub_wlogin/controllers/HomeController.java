package com.teressas.bookclub_wlogin.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.teressas.bookclub_wlogin.models.Book;
import com.teressas.bookclub_wlogin.models.User;
import com.teressas.bookclub_wlogin.services.BookService;
import com.teressas.bookclub_wlogin.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	
	// after user logs in show user's book and books by other user
	// created session from reg and login
	@GetMapping("/books")
    public String dashboard(Model model, HttpSession session) {
		if(session.getAttribute("userId")==null) {
            return "redirect:/";
        }
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        List<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        return "dashboard.jsp";
    }
	
	// shows the add new book form
	@GetMapping("/books/new")
	public String createBookForm(@ModelAttribute("newBook") Book newBook, HttpSession session) {
		if(session.getAttribute("userId")==null) {
            return "redirect:/";
        }
		return "newBookForm.jsp";
	}
	
	// process new book form
	@PostMapping("/books/new") 
	public String processCreateBookForm(@Valid @ModelAttribute("newBook") Book newBook,
			BindingResult result, HttpSession session) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "newBookForm.jsp";			
		} else {
			bookService.saveBook(newBook);
			return  "redirect:/books";
		}
	}
	
	// show one book
	@GetMapping("/books/{id}")
	public String showOneBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/";
		}
		Book book = bookService.findOneBook(id);
		model.addAttribute("book", book);
		return "showOneBook.jsp";
	}
	
	
	// show edit book form
	@GetMapping("/books/{id}/edit")
	public String editOneBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId")==null) {
            return "redirect:/";
        }
		Book book = bookService.findOneBook(id);
		// only the user/writer logged in can edit the book
		Long writerId = book.getWriter().getId();
		
		String userIdString = String.valueOf(session.getAttribute("userId"));
		if(writerId != Long.valueOf(userIdString)) {
			return "redirect:/";
		}

		model.addAttribute("book", book);
		return "editBookForm.jsp";
	}
	
	// process edit book form
	@PostMapping("/books/{id}/edit")
	public String processShowOneBook(@Valid @ModelAttribute("book") Book book,
			BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		Long writerId = book.getWriter().getId();
		
		if(result.hasErrors()) {
			return "editBookForm.jsp";			
		} else {
			bookService.saveBook(book);
			return  "redirect:/books/{id}";
		}
	}
	
	// delete book
	@DeleteMapping("/books/{id}/delete")
	public String processDeleteBook(@PathVariable("id") Long id) {
		bookService.deleteOneBook(id);
		return "redirect:/books";
	}

	// show book market
	@GetMapping("/bookmarket")
	public String showBookMarket(Model model, HttpSession session) {
		if(session.getAttribute("userId")==null) {
            return "redirect:/";
        }
        List<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        return "bookmarket.jsp";
	}
	
	// sets the user as the reader/borrower
	@PutMapping("/books/{bookId}/borrow")
	public String bookBorrower(@PathVariable("bookId")Long BookId, HttpSession session, Model model) {
		Book book = bookService.findOneBook(BookId);
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.getOneUser(userId);
		book.setReader(user);
		
		bookService.saveBook(book);
		return "redirect:/bookmarket";	
	}

	
	// sets the user id in the returnUser field
	@PutMapping("/books/{bookId}/return")
	public String returnBook(@PathVariable("bookId")Long BookId, HttpSession session) {
		Book book = bookService.findOneBook(BookId);
		book.setReader(null);
		bookService.saveBook(book);
		return "redirect:/bookmarket";	
	}
	
	

}
