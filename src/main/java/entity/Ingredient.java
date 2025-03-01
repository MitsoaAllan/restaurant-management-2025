package entity;

import db.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Ingredient {
    private final DataSource dataSource = new DataSource();

    private int idIngredient;
    private String name;
    private double unitPrice;
    private Unit unit;
    private LocalDate updateDateTime;
    private List<IngredientPriceHistory> priceHistory;
    private List<Movement> movements;

    public Ingredient() {

    }
    public Ingredient(String name, double unitPrice, Unit unit, LocalDate updateDateTime,List<IngredientPriceHistory> priceHistory) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.updateDateTime = updateDateTime;
        this.priceHistory = priceHistory;
    }

    public Ingredient(String name, double unitPrice, Unit unit, LocalDate updateDateTime) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.updateDateTime = updateDateTime;
    }
    public Ingredient(int idIngredient,String name, double unitPrice, Unit unit, LocalDate updateDateTime) {
        this.idIngredient = idIngredient;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.updateDateTime = updateDateTime;
    }

    public Ingredient(int idIngredient, String name, double unitPrice, Unit unit, LocalDate updateDateTime, List<IngredientPriceHistory> priceHistory) {
        this.idIngredient = idIngredient;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.updateDateTime = updateDateTime;
        this.priceHistory = priceHistory;
    }

    public Ingredient(int idIngredient, String name, double unitPrice, Unit unit, LocalDate updateDateTime, List<IngredientPriceHistory> priceHistory, List<Movement> movements) {
        this.idIngredient = idIngredient;
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.updateDateTime = updateDateTime;
        this.priceHistory = priceHistory;
        this.movements = movements;
    }

    public int getIdIngredient() { return idIngredient; }
    public void setIdIngredient(int idIngredient) { this.idIngredient = idIngredient; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public Unit getUnit() { return unit; }
    public void setUnit(Unit unit) { this.unit = unit; }

    public LocalDate getUpdateDateTime() { return updateDateTime; }
    public void setUpdateDateTime(LocalDate updateDateTime) { this.updateDateTime = updateDateTime; }

    public List<IngredientPriceHistory> getPriceHistory() {
        return priceHistory;
    }
    public void setPriceHistory(List<IngredientPriceHistory> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public List<Movement> getMovements() {
        return movements;
    }
    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public double getLatestIngredientPrice() {
        return priceHistory.stream()
                .max(Comparator.comparing(IngredientPriceHistory::getDate))
                .map(IngredientPriceHistory::getPrice)
                .orElse(unitPrice);
    }

    public double getIngredientPriceByDate(LocalDate date) {
        return priceHistory.stream()
                .filter((ph) -> !ph.getDate().isAfter(date))
                .max(Comparator.comparing(IngredientPriceHistory::getDate))
                .map(IngredientPriceHistory::getPrice)
                .orElse(unitPrice);
    }

    public double getStockAtDate(LocalDateTime date) throws SQLException {
        try(Connection conn = dataSource.getConnection()) {
            String sql = """
                SELECT
                (SUM(CASE WHEN move_type = 'IN' THEN quantity ELSE 0 END), 0) -
                (SUM(CASE WHEN move_type = 'OUT' THEN quantity ELSE 0 END), 0)
                FROM move
                WHERE id_ingredient = ? AND move.update_move <= ?
            """;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, this.idIngredient);
                stmt.setTimestamp(2, Timestamp.valueOf(date));

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getDouble(1);
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + " (" + unitPrice + " per " + unit + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ingredient that)) return false;
        return idIngredient == that.idIngredient && Double.compare(unitPrice, that.unitPrice) == 0 && Objects.equals(name, that.name) && unit == that.unit && Objects.equals(updateDateTime, that.updateDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIngredient, name, unitPrice, unit, updateDateTime);
    }
}
