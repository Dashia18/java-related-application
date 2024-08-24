--liquibase formatted sql

--changeset daria.serebriakova:24082024 logicalFilePath:classpath:/db-migrations/v1_0/24082024_add_audit_table.sql

CREATE TABLE if not exists audit_entities (
   id bigint NOT NULL AUTO_INCREMENT,
   auditable_entity_type varchar(255) NOT NULL,
   auditable_entity_id bigint NOT NULL,
   operation_type varchar(255) NOT NULL,
   start_operation_time datetime(6),
   end_operation_time datetime(6),
   user_id varchar(255) NOT NULL,
   payload json,
   PRIMARY KEY (id)
) ENGINE=InnoDB;

--rollback drop table audit_entities;
