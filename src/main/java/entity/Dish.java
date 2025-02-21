package entity;

import java.util.Objects;

public class Dish {
    private int id_dish;
    private String name;
    private int unit_price;

    public Dish(String name, int unit_price) {
        this.name = name;
        this.unit_price = unit_price;
    }

    public Dish(){

    }

    public int getId_dish() {
        return id_dish;
    }

    public void setId_dish(int id_dish) {
        this.id_dish = id_dish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id_dish=" + id_dish +
                ", name='" + name + '\'' +
                ", unit_price=" + unit_price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Dish dish)) return false;
        return id_dish == dish.id_dish && unit_price == dish.unit_price && Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_dish, name, unit_price);
    }
}
