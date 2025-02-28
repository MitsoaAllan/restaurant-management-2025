package entity;

public class DishIngredient {
    private final Ingredient ingredient;
    private final double requiredQuantity;

    public DishIngredient(Ingredient ingredient, double requiredQuantity) {
        this.ingredient = ingredient;
        this.requiredQuantity = requiredQuantity;
    }

    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) {
    }

    public double getRequiredQuantity() { return requiredQuantity; }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "ingredient=" + ingredient +
                ", requiredQuantity=" + requiredQuantity +
                '}';
    }
}
