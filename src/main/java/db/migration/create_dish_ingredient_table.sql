CREATE TABLE IF NOT EXISTS dish_ingredient(
    id_dish INT REFERENCES dish(id_dish),
    id_ingredient INT REFERENCES ingredient(id_ingredient),
    required_quantity DECIMAL NOT NULL,
    unit unit NOT NULL,
    CONSTRAINT dish_ingredient_pk PRIMARY KEY (id_dish,id_ingredient)
);

INSERT INTO dish_ingredient (id_dish, id_ingredient, required_quantity, unit) VALUES
    (1,1,100,'G'),
    (1,2,0.15,'L'),
    (1,3,1,'U'),
    (1,4,1,'U');
