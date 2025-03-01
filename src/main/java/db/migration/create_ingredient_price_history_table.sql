CREATE TABLE IngredientPriceHistory (
    id_price SERIAL PRIMARY KEY,
    id_ingredient INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (id_ingredient) REFERENCES Ingredient(id_ingredient)
);
