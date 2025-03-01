package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Movement {
    private int idMove;
    private Ingredient ingredient;
    private MoveType moveType;
    private double quantity;
    private Unit unit;
    private LocalDate updateTime;

    public Movement() {

    }
   public Movement(int idMove, Ingredient ingredient, MoveType moveType, double quantity, Unit unit,LocalDate updateTime) {
       this.idMove = idMove;
       this.ingredient = ingredient;
       this.moveType = moveType;
       this.quantity = quantity;
       this.unit = unit;
       this.updateTime = updateTime;
   }

    public Movement(Ingredient ingredient, MoveType moveType, double quantity,Unit unit, LocalDate updateTime) {
        this.ingredient = ingredient;
        this.moveType = moveType;
        this.quantity = quantity;
        this.unit = unit;
        this.updateTime = updateTime;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getIdMove() {
        return idMove;
    }

    public void setIdMove(int idMove) {
        this.idMove = idMove;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "idMove=" + idMove +
                ", ingredient=" + ingredient +
                ", moveType=" + moveType +
                ", quantity=" + quantity +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movement movement)) return false;
        return idMove == movement.idMove && Double.compare(quantity, movement.quantity) == 0 && Objects.equals(ingredient, movement.ingredient) && moveType == movement.moveType && Objects.equals(updateTime, movement.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMove, ingredient, moveType, quantity, updateTime);
    }
}
