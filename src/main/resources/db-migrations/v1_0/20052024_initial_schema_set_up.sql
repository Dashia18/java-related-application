--liquibase formatted sql

--changeset daria.serebriakova:20052024 logicalFilePath:classpath:/db-migrations/v1_0/20052024_initial_schema_set_up.sql

CREATE TABLE if not exists bank_clients (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  surname varchar(255) NOT NULL,
  address varchar(255),
  PRIMARY KEY (id),
  CONSTRAINT uc_bank_client UNIQUE(name, surname)
) ENGINE=InnoDB;

CREATE TABLE if not exists bank_accounts (
  id bigint NOT NULL AUTO_INCREMENT,
  bank_client_id bigint,
  account_number varchar(255) NOT NULL,
  amount bigint(20),
  is_premium boolean,
  created_date datetime(6),
  updated_date datetime(6),
  PRIMARY KEY (id),
  FOREIGN KEY (bank_client_id) REFERENCES bank_clients (id),
  CONSTRAINT uc_account_number UNIQUE(account_number)
) ENGINE=InnoDB;

--rollback drop table bank_clients;
--rollback drop table bank_accounts;
