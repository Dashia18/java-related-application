--liquibase formatted sql

--changeset daria.serebriakova:25062024 logicalFilePath:classpath:/db-migrations/v1_0/25062024_add_transfer_table.sql

CREATE TABLE if not exists money_transfers (
  id bigint NOT NULL AUTO_INCREMENT,
  from_account varchar(255) NOT NULL,
  from_amount bigint(20),
  to_account varchar(255) NOT NULL,
  to_amount bigint(20),
  amount bigint(20),
  updated_from_amount bigint(20),
  updated_to_amount bigint(20),
  status varchar(255) NOT NULL,
  created_date datetime(6),
  error varchar(255),
  PRIMARY KEY (id)
) ENGINE=InnoDB;

--rollback drop table money_transfers;

