package de.tum.hackatum.hellofresh.web.food;

import de.tum.hackatum.hellofresh.port.in.food.SuggestionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/food/suggestion/")
@AllArgsConstructor
public class SuggestionController {

    private final SuggestionUseCase suggestionUseCase;

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

}
