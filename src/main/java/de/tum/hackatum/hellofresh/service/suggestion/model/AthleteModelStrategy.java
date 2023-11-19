package de.tum.hackatum.hellofresh.service.suggestion.model;

import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.IngredientPreferenceEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.RecipePreferenceEntity;
import de.tum.hackatum.hellofresh.port.in.food.RecipeResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AthleteModelStrategy implements ModelStrategy {


    private final List<RecipePreferenceEntity> preferenceRecipe;
    private final List<IngredientPreferenceEntity> preferenceIngredient;
    private final List<RecipeResponse> alreadySelected;

    public AthleteModelStrategy(List<RecipePreferenceEntity> preferenceRecipe,
                                List<IngredientPreferenceEntity> preferenceIngredient,
                                List<RecipeResponse> alreadySelected) {
        this.preferenceIngredient = preferenceIngredient;
        this.preferenceRecipe = preferenceRecipe;
        this.alreadySelected = alreadySelected;
    }

    @Override
    public int evaluate(RecipeEntity recipeResponse) {
        for (RecipeResponse response : alreadySelected) {
            if (response.getName().equals(recipeResponse.getName())) {
                return 0;
            }
        }

        // pre high protein, chicken 100g - 239kcal 27g protein. 200 - 15 is good
        int weight = 0;

        float proteinPortion = recipeResponse.getProtein() / recipeResponse.getKcal() / 200.0f;

        if (proteinPortion > 15.0f) {
            log.info("The recipe " + recipeResponse.getName() + " has high protein.");
            weight += 2;
        }

        for (IngredientPreferenceEntity entity : preferenceIngredient) {
            IngredientEntity ingredientEntity = entity.getIngredientEntity();
            if (recipeResponse.getIngredients().contains(ingredientEntity)) {
                weight += entity.getWeight();
            }
        }

        String recipe = recipeResponse.getRegionEntity().getName();

        for (RecipePreferenceEntity entity : preferenceRecipe) {
            String name = entity.getRecipeEntity().getRegionEntity().getName();
            if (recipe.equals(name)) {
                weight += entity.getWeight();
            }
        }


        return weight;
    }

}
