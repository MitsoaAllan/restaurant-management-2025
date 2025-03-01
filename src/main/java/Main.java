import dao.DishDAO;
import dao.IngredientDAO;
import dao.MoveDAO;
import entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        LocalDate oldDate = today.minusDays(10);

        MoveDAO moveDAO = new MoveDAO();
        Ingredient saucisse = new Ingredient(1, "Saucisse", 20, Unit.G,LocalDate.now());

        Ingredient huile = new Ingredient(2, "Huile", 10000, Unit.L, today, List.of(
                new IngredientPriceHistory(3, 2,9800,oldDate),
                new IngredientPriceHistory(4, 2,10000,today)
        ));

        Ingredient oeuf = new Ingredient(3, "Oeuf", 1000, Unit.U, today, List.of(
                new IngredientPriceHistory(5, 3,900,oldDate),
                new IngredientPriceHistory(6, 3, 1000,today)
        ));

        Ingredient pain = new Ingredient(4, "Pain", 1000, Unit.U, today, List.of(
                new IngredientPriceHistory(7, 4,800,oldDate),
                new IngredientPriceHistory(8, 4,1000,today)
        ));
        System.out.println(moveDAO.getCurrentStock(saucisse.getIdIngredient()));


    }
}