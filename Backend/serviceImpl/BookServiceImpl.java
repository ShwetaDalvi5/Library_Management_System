package com.library.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BookDto;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
	
    private static final String IMAGE_DIR = "F:\\Project\\Images";
	
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public Book saveBook(BookDto bookDto, MultipartFile image) throws IOException {
		String filename = image.getOriginalFilename();
		
		String imagePath = IMAGE_DIR + image.getOriginalFilename();
        Files.write(Paths.get(imagePath), image.getBytes());

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setLanguage(bookDto.getLanguage());
        book.setPublisher(bookDto.getPublisher());
        book.setDiscription(bookDto.getDiscription());
        book.setImagePath(filename);  // Store only the filename
        book.setCopiesAvailable(bookDto.getCopiesAvailable());
        book.setTotalCopies(bookDto.getTotalCopies());
        book.setImagePath(imagePath);  
        return bookRepository.save(book);
	}

	@Override
    public Book updateBook(BookDto bookDto, Long id, MultipartFile image) throws IOException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setLanguage(bookDto.getLanguage());
            book.setPublisher(bookDto.getPublisher());
            book.setDiscription(bookDto.getDiscription());
            book.setCopiesAvailable(bookDto.getCopiesAvailable());
            book.setTotalCopies(bookDto.getTotalCopies());


            if (image != null && !image.isEmpty()) {
                String imagePath = IMAGE_DIR + image.getOriginalFilename();
                Files.write(Paths.get(imagePath), image.getBytes());
                book.setImagePath(imagePath);
            }
            
            return bookRepository.save(book);
        } else {
            throw new IOException("Book not found");
        }
    }

	 @Override
	    public void deleteBook(Long id) throws Exception {
	        Optional<Book> optionalBook = bookRepository.findById(id);
	        if (optionalBook.isPresent()) {
	            bookRepository.deleteById(id);
	        } else {
	            throw new Exception("Book not found");
	        }
	    }
	 
	@Override
	public List<Book> getBooks() {
		
		return bookRepository.findAll();
	}

	@Override
	public Book findByid(Long id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public Book getBookById(Long id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id).get();
	}
	
	@Override
    public void issueBook(Long id, String username, LocalDate issueDate, LocalDate returnDate) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (book.getCopiesAvailable() > 0) {
                book.setCopiesAvailable(book.getCopiesAvailable() - 1);
                bookRepository.save(book);
            } else {
                throw new Exception("No copies available for this book");
            }
        } else {
            throw new Exception("Book not found");
        }
    }
	
//	@Override
//    public void returnBook(Long id, String username) throws Exception {
//        Optional<Book> optionalBook = bookRepository.findById(id);
//        if (optionalBook.isPresent()) {
//            Book book = optionalBook.get();
//            book.setCopiesAvailable(book.getCopiesAvailable() + 1);
//            bookRepository.save(book);
//        } else {
//            throw new Exception("Book not found");
//        }
//    }
	
	@Override
	public void returnBook(Long id, String username) throws Exception {
	    Optional<Book> optionalBook = bookRepository.findById(id);
	    if (optionalBook.isPresent()) {
	        Book book = optionalBook.get();
	        
	        // Assuming you have a field `totalCopies` that represents the total number of copies for the book
	        int totalCopies = book.getTotalCopies();
	        int availableCopies = book.getCopiesAvailable();
	        
	        // Ensure available copies do not exceed total copies
	        if (availableCopies < totalCopies) {
	            book.setCopiesAvailable(availableCopies + 1);
	            bookRepository.save(book);
	        } else {
	            throw new Exception("All copies of the book are already available. Return is not needed.");
	        }
	    } else {
	        throw new Exception("Book not found");
	    }
	}
	



}
