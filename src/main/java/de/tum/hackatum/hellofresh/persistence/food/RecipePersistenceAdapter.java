package de.tum.hackatum.hellofresh.persistence.food;

import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeRepository;
import de.tum.hackatum.hellofresh.persistence.food.relationship.*;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsMapper;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsRepository;
import de.tum.hackatum.hellofresh.port.out.RecipePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
@Slf4j
public class RecipePersistenceAdapter implements RecipePort {

    private final UserDetailsRepository userDetailsRepository;

    private final RecipePreferenceRepository recipePreferenceRepository;
    private final IngredientPreferenceRepository ingredientPreferenceRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientConstrainRepository ingredientConstrainRepository;

    private UserDetailsEntity getUserDetailsEntity(UserDetails user) {
        Optional<UserDetailsEntity> byUsername = userDetailsRepository.findByUsername(user.getUsername());

        if (byUsername.isEmpty()) {
            log.error("failed to load username by name");
            return null;
        }

        return byUsername.get();
    }

    @Override
    public List<RecipeEntity> preSelectRecipe(UserDetails user) {
        UserDetailsEntity details = Objects.requireNonNull(getUserDetailsEntity(user));
        List<RecipeEntity> recipeEntities = recipeRepository.findAll();
        List<IngredientConstraintEntity> constrains = ingredientConstrainRepository.findAllByUserDetailsEntity(details);
        List<IngredientEntity> ingredientConstrains = new ArrayList<>();

        for (IngredientConstraintEntity constrain : constrains) {
            ingredientConstrains.add(constrain.getIngredientEntity());
        }

        List<RecipeEntity> result = new ArrayList<>();

        boolean added = false;

        for (RecipeEntity recipeEntity : recipeEntities) {
            Set<IngredientEntity> ingredients = recipeEntity.getIngredients();

            for (IngredientEntity ingredientConstrain : ingredientConstrains) {
                if (ingredients.contains(ingredientConstrain)) {
                    added = true;
                    break;
                }
            }

            if (!added) {
                result.add(recipeEntity);
            }

            added = false;
        }

        return result;
    }

    @Override
    public List<RecipePreferenceEntity> getPreferenceRecipes(UserDetails user) {
        UserDetailsEntity details = Objects.requireNonNull(getUserDetailsEntity(user));

        log.info("Load all recipe preferences of " + details.getUsername());

        return recipePreferenceRepository.findAllByUserDetailsEntity(details);
    }

    @Override
    public List<IngredientPreferenceEntity> getPreferenceIngredient(UserDetails user) {
        UserDetailsEntity details = Objects.requireNonNull(getUserDetailsEntity(user));

        log.info("Load all ingredient preferences of " + details.getUsername());

        return ingredientPreferenceRepository.findAllByUserDetailsEntity(details);
    }

}
