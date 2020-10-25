package com.ecutb.assignment.controller;

import com.ecutb.assignment.entity.Book;
import com.ecutb.assignment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){

        return bookService.saveBook(book);
    }



}
