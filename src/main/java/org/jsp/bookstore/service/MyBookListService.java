package org.jsp.bookstore.service;

import java.util.List;

import org.jsp.bookstore.entity.MyBookList;
import org.jsp.bookstore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBookListService {

	@Autowired
	MyBookRepository mybook;
	
	public void saveMyBook(MyBookList book) {
		mybook.save(book);
	}
	
	public List<MyBookList> getAllMyBooks(){
		return mybook.findAll();
	}
	
	public void deleteById(int id) {
		mybook.deleteById(id);
	}
}
