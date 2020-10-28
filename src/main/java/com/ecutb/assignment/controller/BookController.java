package com.ecutb.assignment.controller;

import com.ecutb.assignment.entity.Book;
import com.ecutb.assignment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) boolean sort){
        return bookService.getAll(title, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre,
                                                      @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllInGenre(genre, sort));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author,
                                                       @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllFromAuthor(author, sort));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title,
                                                      @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllByTitle(title, sort));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<List<Book>> getBooksByIsbn(@PathVariable String isbn,
                                                     @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllByIsbn(isbn, sort));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getBooksByAvailability(@RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllAvailable(sort));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.update(book));
    }

    @PostMapping("/new")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable int id){
        return ResponseEntity.ok(bookService.delete(id));
    }

    @PutMapping("/loan/{id}")
    public ResponseEntity<Boolean> loanBook(@PathVariable int id){
        return ResponseEntity.ok(bookService.loan(id));
    }


}
