package de.tum.hackatum.hellofresh.port.in.food;

public interface IngredientConstraintUseCase {
    boolean addIngredientConstrain(String ingredient);

    boolean removeIngredientConstrain(String ingredient);

}
