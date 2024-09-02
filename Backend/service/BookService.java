package com.library.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.model.Book;

public interface BookService {
	
	public Book saveBook(BookDto bookDto, MultipartFile image) throws IOException;
		
    public Book updateBook(BookDto bookDto, Long id, MultipartFile image) throws IOException;
	
	public void deleteBook(Long id) throws Exception;
	
	public List<Book> getBooks();

	public Book findByid(Long id);

	public Book getBookById(Long id);
	
    void issueBook(Long id, String username, LocalDate issueDate, LocalDate returnDate) throws Exception;
	
    public void returnBook(Long bookId, String userName) throws Exception;

}
