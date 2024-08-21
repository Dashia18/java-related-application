--liquibase formatted sql

--changeset daria.serebriakova:21082024 logicalFilePath:classpath:/db-migrations/v1_0/21082024_update_street_and_building.sql

UPDATE bank_clients
SET country = 'USA', updated_date = now()
WHERE country = 'America';

UPDATE bank_clients as main,
    (SELECT id,
		SUBSTRING_INDEX(SUBSTRING_INDEX(street, '. ', 1), '. ', -1) as street_result,
	    SUBSTRING_INDEX(SUBSTRING_INDEX(street, '. ', 2), '. ', -1) as building_result
	FROM bank_clients) as result
SET main.street = result.street_result, main.building = result.building_result
WHERE main.street LIKE '%. %' and main.id = result.id
