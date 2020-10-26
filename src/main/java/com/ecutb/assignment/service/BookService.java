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


    public List<Book> getAll(){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(result::add);
        return result;
    }

    public List<Book> getAllInGenre(String genre){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.getGenre().equals(genre)){
                result.add(book);
            }
        });
    return result;
    }

    public List<Book> getAllFromAuthor(String author){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.getAuthor().equals(author)){
                result.add(book);
            }
        });
        return result;
    }

    public List<Book> getAllAvailable(){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.isAvailable()){
                result.add(book);
            }
        });
        return result;
    }

    public List<Book> getAllByTitle(String title){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.getTitle().equals(title)){
                result.add(book);
            }
        });
        return result;
    }

    public List<Book> getAllByIsbn(String isbn){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.getIsbn().equals(isbn)){
                result.add(book);
            }
        });
        return result;
    }

    public Book findById(int id){
        if (bookRepository.findById(id).isPresent()){
            return bookRepository.findById(id).get();
        }
        return null;
    }

    public Book update(Book book){
        var result = findById(book.getId());
        if (result != null){
            return bookRepository.save(book);
        }
        return null;
    }

    public void delete(int id){
        var result = findById(id);
        if (result != null){
            bookRepository.delete(result);
        }
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

}
