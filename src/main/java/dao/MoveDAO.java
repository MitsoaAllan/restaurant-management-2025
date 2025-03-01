package dao;

import db.DataSource;
import entity.Movement;

import java.sql.*;
import java.util.List;

public class MoveDAO implements CRUDOperations<Movement> {
    private final DataSource dataSource = new DataSource();

    @Override
    public List<Movement> getAll(int size, int page) {
        return List.of();
    }

    public double getCurrentStock(int idIngredient) {
        String sql = "SELECT SUM(CASE WHEN m.move_type = 'IN' THEN quantity ELSE -quantity END) AS current_stock " +
                "FROM move m WHERE id_ingredient = ? GROUP BY id_ingredient";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idIngredient);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("current_stock");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching stock", e);
        }
        return 0;
    }

    public void insertMovement(Movement movement) {
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO move (id_ingredient, move_type,quantity,unit,update_move) VALUES (?, ?, ?, ?, ?)"))
        {
            ps.setInt(1,movement.getIngredient().getIdIngredient());
            ps.setString(2,movement.getMoveType().toString());
            ps.setDouble(3,movement.getQuantity());
            ps.setString(4,movement.getUnit().toString());
            ps.setDate(5, Date.valueOf(movement.getUpdateTime()));
            ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException("Error inserting movement", e);
        }
    }
}
