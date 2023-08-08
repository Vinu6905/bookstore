package org.jsp.bookstore.controller;



import java.util.List;

import org.jsp.bookstore.entity.Book;
import org.jsp.bookstore.entity.MyBookList;
import org.jsp.bookstore.service.BookService;
import org.jsp.bookstore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

	@Autowired
	BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/avaliable_books")
	public ModelAndView getAllBooks() {	
		List<Book> list=service.getAllBooks();
//		ModelAndView m=new  ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book" ,list);
		return new ModelAndView ("bookList" ,"book" ,list);
		
		
	}
	
	@PostMapping("/save")
		public String addBook(@ModelAttribute Book b) {
			service.save(b);
			return "redirect:/avaliable_books";
		}
		
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list=myBookService.getAllMyBooks();		
		model.addAttribute("book" ,list	);
		return "myBooks";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable ("id") int id) {
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());	
		myBookService.saveMyBook(mb);
		return 	"redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable ("id") int  id ,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book", b);
		return "bookEdit";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/avaliable_books";
	}
	}
	

