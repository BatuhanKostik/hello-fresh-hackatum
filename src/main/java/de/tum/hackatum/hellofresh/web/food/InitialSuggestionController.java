package de.tum.hackatum.hellofresh.web.food;

import de.tum.hackatum.hellofresh.port.in.food.InitialSuggestionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food/suggestion/initial")
@RequiredArgsConstructor
public class InitialSuggestionController {


    private final InitialSuggestionUseCase suggestionUseCase;

    @GetMapping
    public ResponseEntity<List<IngredientResult>> getInitialIngredientSuggestion() {
        List<IngredientResult> ingredientSuggestion = suggestionUseCase.getIngredientSuggestion();

        return ResponseEntity.ok(ingredientSuggestion);
    }



}
