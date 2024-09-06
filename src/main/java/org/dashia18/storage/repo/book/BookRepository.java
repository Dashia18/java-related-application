package org.dashia18.storage.repo.book;

import java.util.List;
import org.dashia18.storage.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorNameContaining(String authorName);

    List<Book> findByPagesBetween(int minPages, int maxPages);
}
