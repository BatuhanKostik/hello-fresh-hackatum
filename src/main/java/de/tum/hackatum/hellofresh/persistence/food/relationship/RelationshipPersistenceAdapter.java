package de.tum.hackatum.hellofresh.persistence.food.relationship;

import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientRepository;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeRepository;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsMapper;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsRepository;
import de.tum.hackatum.hellofresh.port.out.RelationshipPort;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RelationshipPersistenceAdapter implements RelationshipPort {

    private final IngredientPreferenceRepository ingredientPreferenceRepository;
    private final RecipePreferenceRepository recipePreferenceRepository;

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UserDetailsMapper userDetailsMapper;
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public boolean addIngredientSuggestion(UserDetails user, String name, int weight) {
        Optional<IngredientEntity> optionalIngredient = ingredientRepository.getIngredientEntityByName(name);

        if (optionalIngredient.isEmpty())
            return false;

        IngredientEntity ingredientEntity = optionalIngredient.get();
        Optional<UserDetailsEntity> byUsername = userDetailsRepository.findByUsername(user.getUsername());

        if (byUsername.isEmpty())
            return false;

        IngredientPreferenceEntity entity = IngredientPreferenceEntity.builder()
                .ingredientEntity(ingredientEntity)
                .userDetailsEntity(byUsername.get())
                .weight(weight)
                .build();

        ingredientPreferenceRepository.save(entity);

        return true;
    }

    @Override
    public boolean addRecipeSuggestion(UserDetails user, String name, int weight) {
        List<RecipeEntity> all = recipeRepository.findAll();

        for (RecipeEntity recipe : all) {
            System.out.println(recipe.toString());
        }


        Optional<RecipeEntity> optional = recipeRepository.getRecipeEntityByName(name);

        if (optional.isEmpty())
            return false;

        RecipeEntity recipe = optional.get();
        Optional<UserDetailsEntity> byUsername = userDetailsRepository.findByUsername(user.getUsername());

        if (byUsername.isEmpty())
            return false;


        RecipePreferenceEntity entity = RecipePreferenceEntity.builder()
                .recipeEntity(recipe)
                .userDetailsEntity(byUsername.get())
                .weight(weight)
                .build();

        recipePreferenceRepository.save(entity);

        return true;
    }

}
