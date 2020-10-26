package com.ecutb.assignment.controller;

import com.ecutb.assignment.entity.Book;
import com.ecutb.assignment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Book getBookById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @GetMapping("/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre){
        return bookService.getAllInGenre(genre);
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author){
        return bookService.getAllFromAuthor(author);
    }

    @GetMapping("/title/{title}")
    public List<Book> getBooksByTitle(@RequestBody String title){
        return bookService.getAllByTitle(title);
    }

    @GetMapping("/isbn/{isbn}")
    public List<Book> getBooksByIsbn(@RequestBody String isbn){
        return bookService.getAllByIsbn(isbn);
    }

    @GetMapping("/available")
    public List<Book> getBooksByAvailability(){
        return bookService.getAllAvailable();
    }

    @PostMapping("/update")
    public Book updateBook(@RequestBody Book book){
        return bookService.update(book);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("delete/{id}")
    public void deleteBook(@PathVariable int id){
        bookService.delete(id);
    }





}
