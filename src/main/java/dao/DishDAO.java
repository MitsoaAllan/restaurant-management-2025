package dao;

import db.DataSource;
import entity.Dish;
import entity.DishIngredient;
import entity.Ingredient;
import entity.Unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDAO implements CRUDOperations<Dish> {
    private final DataSource datasource = new DataSource();

    @Override
    public List<Dish> getAll(int size, int page) {
        List<Dish> dishes = new ArrayList<>();
        String sql = """
                SELECT d.id_dish,d.name,d.unit_price,
                FROM dish_ingredient di
                JOIN ingredient i
                    ON di.id_ingredient = i.id_ingredient
                JOIN Dish d
                    ON d.id_dish = di.id_dish
                LIMIT ? OFFSET ?
                """;
            try(Connection conn = datasource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);)
            {
                ps.setInt(1, size);
                ps.setInt(2, (page-1)*size);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {

                    Dish dish = new Dish();
                    dish.setIdDish(rs.getInt("id_dish"));
                    dish.setName(rs.getString("name"));
                    dish.setUnitPrice(rs.getInt("unit_price"));
                    dishes.add(dish);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return dishes;
    }

    public List<DishIngredient> getIngredientsDish(int idDish) {
        List<DishIngredient> ingredients = new ArrayList<>();
        String sql = """
                SELECT di.id_ingredient,i.name,i.unit_price,di.required_quantity,i.unit,i.update_time
                FROM dish_ingredient di
                LEFT JOIN ingredient i
                ON di.id_ingredient = i.id_ingredient
                RIGHT JOIN Dish d
                ON d.id_dish = di.id_dish
                WHERE d.id_dish = ?
                """;
        try(Connection conn = datasource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1, idDish);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                DishIngredient ingredient = new DishIngredient(
                        new Ingredient(rs.getString("name"),rs.getDouble("unit_price"),Unit.valueOf(rs.getString("unit")),rs.getDate("update_time").toLocalDate(),List.of()),
                        rs.getDouble("required_quantity")
                );
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }

}
