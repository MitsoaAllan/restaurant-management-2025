package entity;

import java.time.LocalDate;

public class IngredientPriceHistory {
    private int idPrice;
    private int idIngredient;
    private double price;
    private LocalDate date;

    public IngredientPriceHistory(int idPrice, int idIngredient, double price, LocalDate date) {
        this.idPrice = idPrice;
        this.idIngredient = idIngredient;
        this.price = price;
        this.date = date;
    }

    public IngredientPriceHistory(int idIngredient, double price, LocalDate date) {
        this(0, idIngredient, price, date);
    }

    public int getIdPrice() { return idPrice; }
    public int getIdIngredient() { return idIngredient; }
    public double getPrice() { return price; }
    public LocalDate getDate() { return date; }
}
