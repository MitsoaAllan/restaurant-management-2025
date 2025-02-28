CREATE TABLE dish(
    id_dish BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    unit_price INT NOT NULL
);

INSERT INTO dish (name, unit_price) VALUES ('Hot Dog',15000);

