package de.tum.hackatum.hellofresh.port.in.suggestion;

import de.tum.hackatum.hellofresh.web.suggestion.IngredientResult;

import java.util.List;

public interface InitialSuggestionUseCase {

    List<IngredientResult> getIngredientSuggestion();

}
