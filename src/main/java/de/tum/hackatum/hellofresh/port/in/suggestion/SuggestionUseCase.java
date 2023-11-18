package de.tum.hackatum.hellofresh.port.in.suggestion;

public interface SuggestionUseCase {

    boolean addIngredientSuggestion(String name, int weight);

    boolean addRecipeSuggestion(String name, int weight);
}
