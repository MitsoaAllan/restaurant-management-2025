package entity;

public class DishIngredient {
    private Ingredient ingredient;
    private double requiredQuantity;

    public DishIngredient(Ingredient ingredient, double requiredQuantity) {
        this.ingredient = ingredient;
        this.requiredQuantity = requiredQuantity;
    }
    public DishIngredient() {

    }

    public double getCost(){
        return requiredQuantity * ingredient.getUnitPrice();
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
