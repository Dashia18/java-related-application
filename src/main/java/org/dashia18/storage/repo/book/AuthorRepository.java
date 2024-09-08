package org.dashia18.storage.repo.book;

import org.dashia18.storage.model.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
