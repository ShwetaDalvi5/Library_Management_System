package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.dto.BookDto;
import com.library.dto.IssueBookRequest;
import com.library.dto.ReturnBookRequest;
import com.library.model.Book;
import com.library.service.BookService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(value = "http://localhost:4200")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestParam("bookDto") String bookDtoJson, @RequestParam("image") MultipartFile image) {
        try {
            BookDto bookDto = objectMapper.readValue(bookDtoJson, BookDto.class);
            Book book = bookService.saveBook(bookDto, image);
            return new ResponseEntity<>(book, HttpStatus.CREATED);         
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestParam("bookDto") String bookDtoJson, @RequestParam("image") MultipartFile image) {
        try {
            BookDto bookDto = objectMapper.readValue(bookDtoJson, BookDto.class);
            Book updatedBook = bookService.updateBook(bookDto, id, image);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allBooks")
    public List<Book> getBooks() {
        return bookService.getBooks().stream()
            .map(book -> {
                book.setImagePath("/api/images/"+ book.getId());
                return book;
            })
            .collect(Collectors.toList());
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImageById(@PathVariable Long id) throws IOException {
        Book book = bookService.findByid(id);
        Path imagePath = Paths.get(book.getImagePath());
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }
    
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    
    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestBody IssueBookRequest issueBookRequest) {
        try {
            System.out.println("Received issue request: " + issueBookRequest); // Log request data
            bookService.issueBook(issueBookRequest.getId(), issueBookRequest.getUsername(), issueBookRequest.getIssueDate(), issueBookRequest.getReturnDate());
            return new ResponseEntity<>("Book issued successfully!", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error issuing book: " + e.getMessage()); // Log error message
            e.printStackTrace(); // Log stack trace
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



//    @PostMapping("/return-book")
//    public ResponseEntity<?> returnBook(@RequestBody ReturnBookRequest returnBookRequest) {
//        try {
//            bookService.returnBook(returnBookRequest.getId(), returnBookRequest.getUsername());
//            return new ResponseEntity<>("Book returned successfully!", HttpStatus.OK);
//        } catch (Exception e) {
//        	e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    
    @PostMapping("/return-book")
    public ResponseEntity<?> returnBook(@RequestBody ReturnBookRequest returnBookRequest) {
        if (returnBookRequest.getId() == null) {
            return new ResponseEntity<>("Book ID must not be null", HttpStatus.BAD_REQUEST);
        }

        try {
            bookService.returnBook(returnBookRequest.getId(), returnBookRequest.getUsername());
            return new ResponseEntity<>("Book returned successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


