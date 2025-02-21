package dao;

import db.DataSource;
import entity.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishCRUDOperations implements CRUDOperations<Dish> {
    private final DataSource datasource = new DataSource();

    @Override
    public List<Dish> getAll(int size, int page) {
        List<Dish> dishes = new ArrayList<>();
        String sql = """
                SELECT * FROM dish LIMIT ? OFFSET ?
                """;
        try(Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,size);
            ps.setInt(2,(page-1)*size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId_dish(rs.getInt("id_dish"));
                dish.setName(rs.getString("name"));
                dish.setUnit_price(rs.getInt("unit_price"));
                dishes.add(dish);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dishes;
    }

    @Override
    public Dish getById(int id) {
        Dish dish = new Dish();
        try(Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM dish WHERE id_dish = ?"))
        {
         ps.setInt(1, id);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             dish = new Dish();
             dish.setId_dish(rs.getInt("id_dish"));
             dish.setName(rs.getString("name"));
             dish.setUnit_price(rs.getInt("unit_price"));
         }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dish;
    }

    @Override
    public List<Dish> saveAll(List<Dish> entities) {
        entities.forEach(entity -> {
           try(Connection conn = datasource.getConnection();
           PreparedStatement ps = conn.prepareStatement("INSERT INTO dish (name, unit_price) VALUES (?, ?)"))
           {
               ps.setString(1, entity.getName());
               ps.setInt(2, entity.getUnit_price());
               ps.executeUpdate();
           }
           catch (SQLException e){
               throw new RuntimeException(e);
           }
        });
        return entities;
    }

//    Mandeha tsara
    @Override
    public Dish update(int id, Dish entity) {
        try(Connection conn = datasource.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE dish SET name=?, unit_price=? WHERE id_dish=?"))
        {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getUnit_price());
            ps.setInt(3, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void delete(int id) {
        try(Connection conn = datasource.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM dish WHERE id_dish=?"))
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
