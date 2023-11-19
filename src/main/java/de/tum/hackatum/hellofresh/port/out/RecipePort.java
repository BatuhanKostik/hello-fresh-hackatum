package de.tum.hackatum.hellofresh.port.out;

import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.IngredientPreferenceEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.RecipePreferenceEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface RecipePort {

    List<RecipeEntity> preSelectRecipe(UserDetails userDetails);

    List<RecipePreferenceEntity> getPreferenceRecipes(UserDetails user);

    List<IngredientPreferenceEntity> getPreferenceIngredient(UserDetails user);
}
