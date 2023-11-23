package com.springboot.camel.postgresql.service;

import com.springboot.camel.postgresql.entity.Book;
import com.springboot.camel.postgresql.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) { 
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookByName(String name) {
        return bookRepository.findBookByName(name);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void removeBook(int bookId) {
        bookRepository.deleteById(bookId);
    }

    public Book updateBook(int bookId, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setName(updatedBook.getName());
            existingBook.setPrice(updatedBook.getPrice());

            return bookRepository.save(existingBook);
        } else {
            return null; 
        }
    }
}
