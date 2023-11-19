package de.tum.hackatum.hellofresh.service.suggestion;

import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.IngredientPreferenceEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.RecipePreferenceEntity;
import de.tum.hackatum.hellofresh.port.in.food.InitialSuggestionUseCase;
import de.tum.hackatum.hellofresh.port.in.food.RecipeResponse;
import de.tum.hackatum.hellofresh.port.in.food.SuggestionUseCase;
import de.tum.hackatum.hellofresh.port.out.RecipePort;
import de.tum.hackatum.hellofresh.port.out.RelationshipPort;
import de.tum.hackatum.hellofresh.port.out.UserPort;
import de.tum.hackatum.hellofresh.service.suggestion.model.ModelStrategy;
import de.tum.hackatum.hellofresh.web.food.IngredientResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SuggestionService implements SuggestionUseCase, InitialSuggestionUseCase {

    private final RelationshipPort relationshipPort;
    private final RecipePort recipePort;
    private final UserPort userPort;
    private final UserModelService userModelService;

    @Override
    public boolean addIngredientSuggestion(String name, int weight) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            log.error("Tried to add ingredient suggestion for " + name + " with a weight of " + weight + ", but the user dont exist.");
            return false;
        }

        log.info("Add new ingredient suggestion for " + name + " with a weight of " + weight + " for the user " + principal.getUsername());


        return relationshipPort.addIngredientSuggestion(principal, name, weight);
    }

    @Override
    public boolean addRecipeSuggestion(String name, int weight) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            log.error("Tried to add ingredient suggestion for " + name + " with a weight of " + weight + ", but the user dont exist.");
            return false;
        }

        log.info("Add new ingredient suggestion for " + name + " with a weight of " + weight + " for the user " + principal.getUsername());

        return relationshipPort.addRecipeSuggestion(principal, name, weight);
    }

    @Override
    public List<IngredientResult> getIngredientSuggestion() {
        //TODO add also here,
        return List.of(
                new IngredientResult("Tomato", "www.google.com")
        );
    }

    public RecipeEntity generateSuggestion(List<RecipeResponse> alreadySelected) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user == null) {
            log.error("Tried to generate suggestion while no user is logged in.");
            return null;
        }

        List<RecipeEntity> preSelected = recipePort.preSelectRecipe(user);
        List<RecipePreferenceEntity> preferenceRecipe = recipePort.getPreferenceRecipes(user);
        List<IngredientPreferenceEntity> preferenceIngredient = recipePort.getPreferenceIngredient(user);

        RecipeEntity result = null;
        int highestScore = 0;
        ModelStrategy model = userModelService.getModel(userPort.getUserModel(user),
                preferenceRecipe,
                preferenceIngredient,
                alreadySelected);

        for (RecipeEntity recipeResponse : preSelected) {
            // ignore already selected

            int evaluate = model.evaluate(recipeResponse);
            if (evaluate > highestScore) {
                highestScore = evaluate;
                result = recipeResponse;
            }
        }

        return result;
    }

}
