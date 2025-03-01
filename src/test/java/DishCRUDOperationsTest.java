import dao.DishDAO;
import dao.IngredientDAO;
import dao.MoveDAO;
import db.DataSource;
import entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DishCRUDOperationsTest {
    private final IngredientDAO subjectIngredient = new IngredientDAO();
    private final Dish dish = new Dish();

    LocalDate today = LocalDate.now();
    LocalDate oldDate = today.minusDays(10);

    Ingredient saucisse = new Ingredient(1, "Saucisse", 20, Unit.G, today, List.of(
            new IngredientPriceHistory(1, 1, 18,oldDate),
            new IngredientPriceHistory(2, 1,20,today)
    ));

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

    List<DishIngredient> ingredients = List.of(
            new DishIngredient(saucisse, 100),
            new DishIngredient(huile, 0.15),
            new DishIngredient(oeuf, 1),
            new DishIngredient(pain, 1)
    );

    Dish hotDog = new Dish("Hot Dog", 15000,ingredients);


    @Test
    void ingredient_price_equals_dish_price_ok(){
        double expected = 5500;
        double actual = hotDog.getIngredientCost();

        assertEquals(expected, actual);

    }
    @Test
    void get_ingredients_filter_by_name_and_unit_price_and_paginate_ok(){
        Criteria criteria = new Criteria("name","u", "ILIKE",true,true,"AND");
        Criteria criteria1 = new Criteria("unit_price","1000", "<=",false,false,"AND");

        List<Ingredient> expected = List.of(saucisse,oeuf);
        List<Ingredient> actual = subjectIngredient.getIngredientsByCriteria(List.of(criteria,criteria1),2,1);

        assertNotNull(actual);
        haveACommonElement(expected,actual);

    }

    @Test
    void get_ingredient_cost_by_date_ok() {
        double expectedCost =4970;
        double actual = hotDog.getIngredientCost(oldDate);

        assertEquals(expectedCost,actual);
    }

    @Test
    void get_latest_gross_margin(){
        double expected = 9500;
        double actual = hotDog.getGrossMargin();

        assertEquals(expected,actual);
    }

    @Test
    void get_gross_margin_by_date(){
        double expected = 10030;
        double actual = hotDog.getGrossMarginByDate(oldDate);

        assertEquals(expected,actual);
    }

    @Test
    void get_dish_ingredient_by_id_dish_ok(){
        DishDAO subject = new DishDAO();
        List<DishIngredient> expected = List.of(
                new DishIngredient(saucisse, 100),
                new DishIngredient(huile, 0.15),
                new DishIngredient(oeuf, 1),
                new DishIngredient(pain, 1)
        );
        List<DishIngredient> actual =subject.getIngredientsDish(1);

        assertNotNull(actual);
        haveACommonElement(expected,actual);
    }

    @Test
    void get_current_stock_with_hot_dog_ok(){
        double expectedSaucisse =10000;
        double expectedHuile =20;
        double expectedOeuf =80;
        double expectedPain =30;

        MoveDAO subject = new MoveDAO();

        double actualSaucisse = subject.getCurrentStock(saucisse.getIdIngredient());
        double actualHuile = subject.getCurrentStock(huile.getIdIngredient());
        double actualOeuf = subject.getCurrentStock(oeuf.getIdIngredient());
        double actualPain = subject.getCurrentStock(pain.getIdIngredient());

        assertEquals(expectedSaucisse,actualSaucisse);
        assertEquals(expectedHuile,actualHuile);
        assertEquals(expectedOeuf,actualOeuf);
        assertEquals(expectedPain,actualPain);
    }

    @Test
    void get_current_stock_with_salt_and_rice_ok(){
        Ingredient salt = new Ingredient(5,"sel",2.5,Unit.G,today);
        Ingredient rice = new Ingredient(6,"riz",3.5,Unit.G,today);

        double expectedSalt = 450;
        double expectedRice = 500;

        MoveDAO subject = new MoveDAO();

        double actualSalt = subject.getCurrentStock(salt.getIdIngredient());
        double actualRice = subject.getCurrentStock(rice.getIdIngredient());

        assertEquals(expectedSalt,actualSalt);
        assertEquals(expectedRice,actualRice);
    }

    private static boolean haveACommonElement(Collection<?> collection1,
                                              Collection<?> collection2) {
        Set<?> set = new HashSet<>(collection1);
        set.retainAll(collection2);
        return !set.isEmpty();
    }
}
