--liquibase formatted sql

--changeset daria.serebriakova:06092024 logicalFilePath:classpath:/db-migrations/v1_0/06092024_create_book.sql

CREATE TABLE if not exists authors (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE if not exists books (
  id bigint NOT NULL AUTO_INCREMENT,
  title varchar(255) NOT NULL,
  pages INTEGER,
  author_id bigint,
  PRIMARY KEY (id),
  FOREIGN KEY (author_id) REFERENCES authors (id)
) ENGINE=InnoDB;

INSERT INTO authors (id, name) VALUES (1, 'Joshua Bloch');
INSERT INTO authors (id, name) VALUES (2, 'Robert C. Martin');

INSERT INTO books (id, title, pages, author_id) VALUES (1, 'Effective Java', 416, 1);
INSERT INTO books (id, title, pages, author_id) VALUES (2, 'Clean Code', 464, 2);

--rollback drop table books;
--rollback drop table authors;
