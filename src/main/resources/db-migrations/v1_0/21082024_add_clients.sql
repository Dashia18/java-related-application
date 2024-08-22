--liquibase formatted sql

--changeset daria.serebriakova:21082024 logicalFilePath:classpath:/db-migrations/v1_0/21082024_add_clients.sql

INSERT INTO bank_clients (id, name, surname, address)
VALUES (1, 'Moly', 'Miles', 'Sunny ave. 34');

INSERT INTO bank_clients (id, name, surname, address)
VALUES (2, 'Jorge', 'Ruby', 'Sunny ave. 78');

INSERT INTO bank_clients (id, name, surname, address)
VALUES (3, 'Gorge', 'Smite', 'Rainy st. 2');

INSERT INTO bank_clients (id, name, surname, address)
VALUES (4, 'Mike', 'Hill', 'Cloudy st. 290');

INSERT INTO bank_accounts (id, bank_client_id, account_number, amount, is_premium, created_date, updated_date)
VALUES (1, 1, '447c7c7b-fb2f-4aaa-9900-21d7ed27d01b', 10000, false, now(), now());

INSERT INTO bank_accounts (id, bank_client_id, account_number, amount, is_premium, created_date, updated_date)
VALUES (2, 2, '447c7c7b-fb2f-4aaa-9900-21d7ed27d02b', 15000, true, now(), now());

INSERT INTO bank_accounts (id, bank_client_id, account_number, amount, is_premium, created_date, updated_date)
VALUES (3, 3, '447c7c7b-fb2f-4aaa-9900-21d7ed27d03b', 7000, false, now(), now());

--rollback delete from bank_clients where id = 1;
--rollback delete from bank_clients where id = 2;
--rollback delete from bank_clients where id = 3;
--rollback delete from bank_clients where id = 4;

--rollback delete from bank_accounts where id = 1;
--rollback delete from bank_accounts where id = 2;
--rollback delete from bank_accounts where id = 3;

