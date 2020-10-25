package com.ecutb.assignment.service;

import com.ecutb.assignment.entity.Book;
import com.ecutb.assignment.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public List<Book> getAllBooks(){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(result::add);
        return result;

    }


    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

}
