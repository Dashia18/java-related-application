package org.dashia18.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dashia18.storage.model.book.Book;
import org.dashia18.storage.repo.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorNameContaining(authorName);
    }

    public List<Book> getBooksByPages(int minPages, int maxPages) {
        return bookRepository.findByPagesBetween(minPages, maxPages);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setPages(bookDetails.getPages());
            book.setAuthor(bookDetails.getAuthor());
            return bookRepository.save(book);
        }).orElse(null);
    }

    public boolean deleteBook(Long id) {
        return bookRepository.findById(id).map(book -> {
            bookRepository.delete(book);
            return true;
        }).orElse(false);
    }
}
