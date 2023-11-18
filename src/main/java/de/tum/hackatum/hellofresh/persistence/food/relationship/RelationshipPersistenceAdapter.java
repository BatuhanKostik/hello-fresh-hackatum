package de.tum.hackatum.hellofresh.persistence.food.relationship;

import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientRepository;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeRepository;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsMapper;
import de.tum.hackatum.hellofresh.port.out.RelationshipPort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RelationshipPersistenceAdapter implements RelationshipPort {

    private final IngredientPreferenceRepository ingredientPreferenceRepository;
    private final RecipePreferenceRepository recipePreferenceRepository;

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UserDetailsMapper userDetailsMapper;

    @Override
    public boolean addIngredientSuggestion(UserDetails user, String name, int weight) {
        Optional<IngredientEntity> optionalIngredient = ingredientRepository.getIngredientEntityByName(name);

        if (optionalIngredient.isEmpty())
            return false;

        IngredientEntity ingredientEntity = optionalIngredient.get();

        IngredientPreferenceEntity entity = IngredientPreferenceEntity.builder()
                .ingredientEntity(ingredientEntity)
                .userDetailsEntity(userDetailsMapper.mapToUserDetailsEntity(user))
                .weight(weight)
                .build();

        ingredientPreferenceRepository.save(entity);

        return true;
    }

    @Override
    public boolean addRecipeSuggestion(UserDetails user, String name, int weight) {
        Optional<RecipeEntity> optional = recipeRepository.getRecipeEntityByName(name);

        if (optional.isEmpty())
            return false;

        RecipeEntity recipe = optional.get();

        RecipePreferenceEntity entity = RecipePreferenceEntity.builder()
                .recipeEntity(recipe)
                .userDetailsEntity(userDetailsMapper.mapToUserDetailsEntity(user))
                .weight(weight)
                .build();

        recipePreferenceRepository.save(entity);

        return true;
    }

}
