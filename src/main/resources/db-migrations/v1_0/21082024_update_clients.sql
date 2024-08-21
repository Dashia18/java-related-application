--liquibase formatted sql

--changeset daria.serebriakova:21082024 logicalFilePath:classpath:/db-migrations/v1_0/21082024_update_clients.sql

alter table bank_clients add column date_of_birth datetime(6) after surname;
alter table bank_clients add column country varchar(255) after date_of_birth;

alter table bank_clients change address street varchar(255) after country;
alter table bank_clients add column building int after street;
alter table bank_clients add column flat int after building;

alter table bank_clients add column updated_date datetime(6) after flat;

UPDATE bank_clients
SET country = 'America', flat = 75, date_of_birth = '2001-10-20 00:00:00.118', updated_date = now()
WHERE id = 1;

UPDATE bank_clients
SET country = 'America', flat = 92, date_of_birth = '1990-03-15 00:00:00.118', updated_date = now()
WHERE id = 2;

UPDATE bank_clients
SET country = 'Italia', flat = 16, date_of_birth = '1995-09-19 00:00:00.118', updated_date = now()
WHERE id = 3;

UPDATE bank_clients
SET country = 'Germany', flat = 138, date_of_birth = '1987-01-13 00:00:00.118', updated_date = now()
WHERE id = 4;

alter table bank_clients modify column date_of_birth datetime(6) not null;
alter table bank_clients modify column updated_date datetime(6) not null;

--rollback alter table bank_clients change street address varchar(255);
--rollback alter table bank_clients drop building;
--rollback alter table bank_clients drop country;
--rollback alter table bank_clients drop flat;
--rollback alter table bank_clients drop date_of_birth;
