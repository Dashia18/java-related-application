--liquibase formatted sql

--changeset daria.serebriakova:14082024 logicalFilePath:classpath:/db-migrations/v1_0/14082024_add_file_table.sql

CREATE TABLE if not exists files (
  id bigint NOT NULL AUTO_INCREMENT,
  file_name varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  file_size bigint(20),
  meta_info varchar(255),
  created_date datetime(6),
  PRIMARY KEY (id)
) ENGINE=InnoDB;

INSERT INTO files (id, file_name, name, file_size, meta_info, created_date)
VALUES (1, 'flysch.jpg','Flysch in Basque country', 360,
"cloudy, ocean, mountain, Spain, Basque country, flysch", now());

INSERT INTO files (id, file_name, name, file_size, meta_info, created_date)
VALUES (2, 'owl.jpg','Owl', 295,
"Owl, forest, summer", now());

--rollback drop table files;
