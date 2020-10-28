package com.ecutb.assignment.service;

import com.ecutb.assignment.entity.Book;
import com.ecutb.assignment.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll(String title, boolean sort){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        if (title != null){
            books.forEach(book -> {
                if (book.getTitle().equals(title)){
                    result.add(book);
                }
            });
        }else{
            books.forEach(result::add);
        }

        if (sort){
            result.sort(Comparator.comparing(Book::getTitle));
        }

        return result;
    }

    public List<Book> getAllInGenre(String genre, boolean sort){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.getGenre().equals(genre)){
                result.add(book);
            }
        });
        if (result.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any books by genre %s.", genre));
        }
        if (sort){
            result.sort(Comparator.comparing(Book::getTitle));
        }
        return result;
    }

    public List<Book> getAllFromAuthor(String author, boolean sort){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.getAuthor().equals(author)){
                result.add(book);
            }
        });
        if (result.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any books by author %s.", author));
        }
        if (sort){
            result.sort(Comparator.comparing(Book::getTitle));
        }
        return result;
    }

    public List<Book> getAllAvailable(boolean sort){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.isAvailable()){
                result.add(book);
            }
        });
        if (result.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any available books."));
        }
        if (sort){
            result.sort(Comparator.comparing(Book::getTitle));
        }

        return result;
    }

    public List<Book> getAllByTitle(String title, boolean sort){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.getTitle().equals(title)){
                result.add(book);
            }
        });
        if (result.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any books by title %s.", title));
        }
        if (sort){
            result.sort(Comparator.comparing(Book::getTitle));
        }
        return result;
    }

    public List<Book> getAllByIsbn(String isbn, boolean sort){
        var books = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        books.forEach(book -> {
            if (book.getIsbn().equals(isbn)){
                result.add(book);
            }
        });
        if (result.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any books by isbn %s", isbn));
        }
        if (sort){
            result.sort(Comparator.comparing(Book::getTitle));
        }
        return result;
    }

    public Book findById(int id){
        if (bookRepository.findById(id).isPresent()){
            return bookRepository.findById(id).get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any books by id %s", id));
        }
    }

    public boolean update(Book book){
        var result = findById(book.getId());
        if (result != null){
           bookRepository.save(book);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any books by id %s", book.getId()));
        }
        return true;
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }


    public boolean delete(int id){
        var result = findById(id);
        if (result != null){
            bookRepository.delete(result);
            var deletedBook = findById(result.getId());
            if (deletedBook != null){
                //Om boken ej kan raderas trots att den hittas måste en bugg ha uppstått - egentligen överflödig i denna applikation
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return true;
    }

    public boolean loan(int id){
        var book = findById(id);
        if (book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        book.setAvailable(false);
        return true;
    }


}
