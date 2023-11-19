package de.tum.hackatum.hellofresh.web.food;

import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.port.in.food.SuggestionUseCase;
import de.tum.hackatum.hellofresh.service.suggestion.SuggestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food/suggestion/")
@AllArgsConstructor
public class SuggestionController {

    private final SuggestionUseCase suggestionUseCase;
    private final SuggestionService suggestionService;

    @PostMapping("ingredient")
    public ResponseEntity<String> addIngredientPreference(@RequestParam String name, @RequestParam  int weight){
        boolean accepted = suggestionUseCase.addIngredientSuggestion(name, weight);

        if (accepted)
            return ResponseEntity.ok().body("Added");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @PostMapping("recipe")
    public ResponseEntity<String> addRecipePreference(@RequestParam String name, @RequestParam int weight) {
        boolean accepted = suggestionUseCase.addRecipeSuggestion(name, weight);

        if (accepted)
            return ResponseEntity.ok().body("Added");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @GetMapping
    public ResponseEntity<RecipeEntity> load() {
        RecipeEntity recipe = suggestionService.generateSuggestion(List.of());

        return ResponseEntity.ok(recipe);
    }

}
