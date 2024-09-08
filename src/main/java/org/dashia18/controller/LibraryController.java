package org.dashia18.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dashia18.service.LibraryService;
import org.dashia18.storage.model.book.Author;
import org.dashia18.storage.model.book.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @QueryMapping
    public List<Book> books() {
        return libraryService.getAllBooks();
    }

    /*
    The @QueryMapping annotation binds this method to a query, a field under the Query type.
    The query field is then determined from the method name, book.

    Use the @Argument annotation to have an argument bound to a target object and injected into the handler method.
     */
    @QueryMapping
    public Book book(@Argument Long id) {
        return libraryService.getBookById(id);
    }

    @QueryMapping
    public List<Book> booksByTitle(@Argument String title) {
        return libraryService.getBooksByTitle(title);
    }

    @QueryMapping
    public List<Book> booksByAuthorName(@Argument String authorName) {
        return libraryService.getBooksByAuthorName(authorName);
    }

    @QueryMapping
    public List<Book> booksByPages(@Argument int minPages, @Argument int maxPages) {
        return libraryService.getBooksByPages(minPages, maxPages);
    }

    /*
    The @SchemaMapping annotation in Spring GraphQL is a powerful tool for resolving nested fields and
    implementing custom logic for specific fields in your GraphQL schema.
    It enhances the modularity, maintainability, and flexibility of your GraphQL resolvers by allowing you
    to separate the data fetching logic for individual fields from the main query and mutation resolvers.
     */
    @SchemaMapping
    public Author author(Book book) {
        return libraryService.getByBook(book);
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument int pages, @Argument Long authorId) {
        Book book = new Book();
        book.setTitle(title);
        book.setPages(pages);
        Author author = libraryService.getAuthorById(authorId);
        book.setAuthor(author);
        return libraryService.save(book);
    }
}
