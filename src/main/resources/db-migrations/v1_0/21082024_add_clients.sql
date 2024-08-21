--liquibase formatted sql

--changeset daria.serebriakova:21082024 logicalFilePath:classpath:/db-migrations/v1_0/21082024_add_clients.sql

INSERT INTO bank_clients (id, name, surname, address)
VALUES (1, 'Moly', 'Miles', 'sunny ave. 34');

INSERT INTO bank_clients (id, name, surname, address)
VALUES (2, 'Jorge', 'Ruby', 'sunny ave. 78');

INSERT INTO bank_clients (id, name, surname, address)
VALUES (3, 'Gorge', 'Smite', 'Rainy st. 2');

INSERT INTO bank_clients (id, name, surname, address)
VALUES (4, 'Mike', 'Hill', 'Cloudy st. 290');

--rollback delete from bank_clients where id = 1;
--rollback delete from bank_clients where id = 2;
--rollback delete from bank_clients where id = 3;
--rollback delete from bank_clients where id = 4;

