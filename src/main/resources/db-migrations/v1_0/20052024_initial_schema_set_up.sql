--liquibase formatted sql

--changeset daria.serebriakova:20052024 logicalFilePath:classpath:/db-migrations/v1_0/20052024_initial_schema_set_up.sql

CREATE TABLE if not exists bank_accounts (
  id bigint NOT NULL AUTO_INCREMENT,
  bank_client_id bigint(20) NOT NULL,
  account_number int NOT NULL,
  amount bigint(20),
  is_premium boolean,
  PRIMARY KEY (id),
  FOREIGN KEY (bank_client_id) REFERENCES bank_clients (id)
) ENGINE=InnoDB;


CREATE TABLE if not exists bank_clients (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  surname varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

--rollback drop table bank_accounts;
--rollback drop table bank_clients;