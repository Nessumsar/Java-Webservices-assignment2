package com.ecutb.assignment.controller;

import com.ecutb.assignment.entity.Book;
import com.ecutb.assignment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/open")
    public List<Book> getAllBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) boolean sort){
        return bookService.getAll(title, sort);
    }

    @GetMapping("/open/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/open/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre,
                                                      @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllInGenre(genre, sort));
    }

    @GetMapping("/open/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author,
                                                       @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllFromAuthor(author, sort));
    }

    @GetMapping("/open/title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title,
                                                      @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllByTitle(title, sort));
    }

    @GetMapping("/open/isbn/{isbn}")
    public ResponseEntity<List<Book>> getBooksByIsbn(@PathVariable String isbn,
                                                     @RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllByIsbn(isbn, sort));
    }

    @GetMapping("/open/available")
    public ResponseEntity<List<Book>> getBooksByAvailability(@RequestParam(required = false) boolean sort){
        return ResponseEntity.ok(bookService.getAllAvailable(sort));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/update")
    public ResponseEntity<Boolean> updateBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.update(book));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/new")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable int id){
        return ResponseEntity.ok(bookService.delete(id));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/loan/{id}")
    public ResponseEntity<Boolean> loanBook(@PathVariable int id){
        return ResponseEntity.ok(bookService.loan(id));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/return/{id}")
    public ResponseEntity<Boolean> returnBook(@PathVariable int id){
        return ResponseEntity.ok(bookService.returnLoan(id));
    }


}
