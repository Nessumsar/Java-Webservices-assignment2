package com.ecutb.assignment.controller;

import com.ecutb.assignment.entity.Book;
import com.ecutb.assignment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre){
        return ResponseEntity.ok(bookService.getAllInGenre(genre));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author){
        return ResponseEntity.ok(bookService.getAllFromAuthor(author));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.getAllByTitle(title));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<List<Book>> getBooksByIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(bookService.getAllByIsbn(isbn));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getBooksByAvailability(){
        return ResponseEntity.ok(bookService.getAllAvailable());
    }

    @PutMapping("/update")
    public void updateBook(@RequestBody Book book){
        bookService.update(book);
    }

    @PostMapping("/new")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }

    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable int id){
        bookService.delete(id);
    }

    @PutMapping("/loan/{id}")
    public void loanBook(@PathVariable int id){

    }


}
