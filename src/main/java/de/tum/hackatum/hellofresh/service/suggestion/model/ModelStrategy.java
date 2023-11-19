package de.tum.hackatum.hellofresh.service.suggestion.model;

import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.port.in.food.RecipeResponse;

public interface ModelStrategy {

    int evaluate(RecipeEntity recipeResponse);

}
