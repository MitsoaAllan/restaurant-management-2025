BEGIN;

CREATE TYPE unit as ENUM('G','L','U');

COMMIT;

CREATE TABLE ingredient(
    id_ingredient BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    unit_price INT NOT NULL,
    unit unit NOT NULL,
    update_time DATE NOT NULL
);

INSERT INTO ingredient
    (name, unit_price, unit, update_time)
    VALUES ('Saucisse', 20, 'G', now()),
            ('Huile', 10000, 'L', now()),
            ('Oeuf', 1000, 'U', now()),
            ('Pain', 1000, 'U', now())
    ;
