package org.dashia18.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dashia18.service.BookService;
import org.dashia18.storage.model.book.Book;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookResolver implements GraphQLQueryResolver {
    private final BookService bookService;

    public List<Book> books() {
        return bookService.getAllBooks();
    }

    public Book book(Long id) {
        return bookService.getBookById(id);
    }

    public List<Book> booksByTitle(String title) {
        return bookService.getBooksByTitle(title);
    }

    public List<Book> booksByAuthorName(String authorName) {
        return bookService.getBooksByAuthorName(authorName);
    }

    public List<Book> booksByPages(int minPages, int maxPages) {
        return bookService.getBooksByPages(minPages, maxPages);
    }
}
