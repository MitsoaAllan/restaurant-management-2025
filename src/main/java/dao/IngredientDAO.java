package dao;

import db.DataSource;
import entity.Criteria;
import entity.Ingredient;
import entity.Unit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO implements CRUDOperations<Ingredient> {
    private final DataSource dataSource = new DataSource();
    @Override
    public List<Ingredient> getAll(int size, int page) {
        List<Ingredient> ingredients = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT i.id_ingredient,i.name,i.unit_price,i.unit FROM ingredient i LIMIT ? OFFSET ?"))
        {
            ps.setInt(1, size);
            ps.setInt(2, (page-1)*size);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIdIngredient(rs.getInt("id_ingredient"));
                ingredient.setName(rs.getString("name"));
                ingredient.setUnitPrice(rs.getDouble("unit_price"));
                ingredient.setUnit(Unit.valueOf(rs.getString("unit")));
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return ingredients;
    }

    public List<Ingredient> getIngredientsByCriteria(List<Criteria> criteriaList, int size, int page) {
        StringBuilder sql = new StringBuilder("SELECT i.id_ingredient,i.name,i.unit_price,i.unit FROM ingredient i WHERE 1=1");
        List<Ingredient> ingredients = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();)
        {
            for(Criteria criteria : criteriaList) {
                switch (criteria.getFieldName()) {
                    case "name":
                        sql.append(" ").append(criteria.getLogic()).append(" i.name ILIKE '%").append(criteria.getValue()).append("%'");
                        break;
                    case "unit_price":
                        sql.append(" ").append(criteria.getLogic()).append(" i.unit_price ").append(criteria.getOperator()).append(criteria.getValue());
                        break;
                    default:
                        throw new UnsupportedOperationException("Unsupported field: " + criteria.getFieldName());
                }
            }
            for(Criteria criteria2 : criteriaList) {
                if (criteria2.isOrderBy()) {
                    sql.append(" ORDER BY ").append(criteria2.getFieldName());
                    if (criteria2.isAsc()) {
                        sql.append(" ASC");
                    }
                }else{
                    break;
                }
            }
            sql.append(" LIMIT ? OFFSET ?");
           PreparedStatement ps = conn.prepareStatement(sql.toString());
           ps.setInt(1, size);
           ps.setInt(2, (page-1)*size);
           ResultSet rs = ps.executeQuery();
           while(rs.next()) {
               Ingredient ingredient = new Ingredient();
               ingredient.setIdIngredient(rs.getInt("id_ingredient"));
               ingredient.setName(rs.getString("name"));
               ingredient.setUnitPrice(rs.getDouble("unit_price"));
               ingredient.setUnit(Unit.valueOf(rs.getString("unit")));
               ingredients.add(ingredient);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    public void saveAll(List<Ingredient> ingredients) {
        ingredients.forEach(ingredient -> {
            try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO ingredient (name, unit_price, unit, update_time) VALUES (?, ?, ?, ?)");)
            {
                ps.setString(1, ingredient.getName());
                ps.setDouble(2, ingredient.getUnitPrice());
                ps.setString(3, ingredient.getUnit().toString());
                ps.setDate(4,Date.valueOf(ingredient.getUpdateDateTime()));
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
