package de.tum.hackatum.hellofresh.port.in.food;

import de.tum.hackatum.hellofresh.web.food.IngredientResult;

import java.util.List;

public interface InitialSuggestionUseCase {

    List<IngredientResult> getIngredientSuggestion();

}
