package com.springboot.camel.postgresql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.camel.postgresql.entity.Book;
import com.springboot.camel.postgresql.repository.BookRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void testFindAllBooks() {
        // Mocking the bookRepository's behavior
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book(1, "Book 1", "Author 1", 20.0));
        mockBooks.add(new Book(2, "Book 2", "Author 2", 25.0));

        Mockito.when(bookRepository.findAll()).thenReturn(mockBooks);
//        when(bookRepository.findAll()).thenReturn(mockBooks);
        List<Book> books = bookService.findAllBooks();
        Assertions.assertEquals(2, books.size());

//        assertNotNull(books);
//        assertEquals(2, books.size());
    }

    @Test
    public void testFindBookByName() {
        // Mocking the bookRepository's behavior
        Book mockBook = new Book(1, "Book 1", "Author 1", 20.0);
        
        Mockito.when(bookRepository.findBookByName("Book 1")).thenReturn(mockBook);
//        when(bookRepository.findBookByName("Book 1")).thenReturn(mockBook);

        Book book = bookService.findBookByName("Book 1");
        Assertions.assertEquals("Book 1", book.getName());
//        assertNotNull(book);
//        assertEquals("Book 1", book.getName());
    }

    // Add similar tests for addBook, removeBook, and updateBook methods in a similar fashion.
    // Mock the behavior of bookRepository methods and test the expected behavior of BookService methods.

     @Test
    public void testAddBook() {
        // Creating a mock book
        Book newBook = new Book(1, "New Book", "New Author", 30.0);

        // Mocking the bookRepository's behavior
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(newBook);
//        when(bookRepository.save(any(Book.class))).thenReturn(newBook);

        Book addedBook = bookService.addBook(newBook);
        
        Assertions.assertNotNull(addedBook);
        Assertions.assertEquals("New Book", addedBook.getName());
        Assertions.assertEquals("New Author", addedBook.getAuthor());
        Assertions.assertEquals(30.0, addedBook.getPrice());
//        assertNotNull(addedBook);
//        assertEquals("New Book", addedBook.getName());
//        assertEquals("New Author", addedBook.getAuthor());
//        assertEquals(30.0, addedBook.getPrice());

        // Verifying that the save method of bookRepository was called once
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any(Book.class));
        //        verify(bookRepository).save(any(Book.class));
        
    }

    @Test
    public void testRemoveBook() {
        // Mocking the bookRepository's behavior
        bookService.removeBook(1);

        // Verifying that the deleteById method of bookRepository was called once with bookId = 1
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(1);
//        verify(bookRepository).deleteById(1);
        
    }

    @Test
    public void testUpdateBook() {
        // Creating a mock book and Optional for findById
        Book updatedBook = new Book(1, "Updated Book", "Updated Author", 35.0);
        Optional<Book> optionalBook = Optional.of(new Book(1, "Old Book", "Old Author", 25.0));

        // Mocking the bookRepository's behavior
        Mockito.when(bookRepository.findById(1)).thenReturn(optionalBook);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(updatedBook);
//        when(bookRepository.findById(1)).thenReturn(optionalBook);
//        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        Book result = bookService.updateBook(1, updatedBook);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Updated Book", result.getName());
        Assertions.assertEquals("Updated Author", result.getAuthor());
        Assertions.assertEquals(35.0, result.getPrice());
//        assertNotNull(result);
//        assertEquals("Updated Book", result.getName());
//        assertEquals("Updated Author", result.getAuthor());
//        assertEquals(35.0, result.getPrice());

        // Verifying that the findById and save methods of bookRepository were called
        Mockito.verify(bookRepository, Mockito.times(1)).findById(1);
        Mockito.verify(bookRepository, Mockito.times(1)).save(Mockito.any(Book.class));
        //        verify(bookRepository).findById(1);
//        verify(bookRepository).save(any(Book.class));
    }

}

