package entity;

import db.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class Dish {
    private int idDish;
    private String name;
    private int unitPrice;
    private List<DishIngredient> ingredients;
    private final DataSource dataSource = new DataSource();


    public Dish() {

    }

    public Dish(String name, int unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public Dish(int idDish, String name, int unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public Dish(int idDish, String name, int unitPrice, List<DishIngredient> ingredients) {
        this.idDish = idDish;
        this.name = name;
        this.unitPrice = unitPrice;
        this.ingredients = ingredients;
    }

    public Dish(String name, int unitPrice, List<DishIngredient> ingredients) {
        this(0, name, unitPrice, ingredients);
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setIngredients(List<DishIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getIdDish() { return idDish; }
    public String getName() { return name; }
    public int getUnitPrice() { return unitPrice; }
    public List<DishIngredient> getIngredients() { return ingredients; }

    public double getIngredientCost(LocalDate date) {
        return ingredients.stream()
                .mapToDouble((di) -> di.getRequiredQuantity() * di.getIngredient().getIngredientPriceByDate(date))
                .sum();
    }

    public double getIngredientCost() {
        return ingredients.stream()
                .mapToDouble((di) -> di.getRequiredQuantity() * di.getIngredient().getLatestIngredientPrice())
                .sum();
    }

    public double getGrossMarginByDate(LocalDate date){
        return unitPrice - getIngredientCost(date);
    }

    public double getGrossMargin(){
        return unitPrice - getIngredientCost();
    }

    @Override
    public String toString() {
        return "Dish{" +
                "idDish=" + idDish +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
