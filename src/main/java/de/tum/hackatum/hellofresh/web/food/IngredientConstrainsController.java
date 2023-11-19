package de.tum.hackatum.hellofresh.web.food;

import de.tum.hackatum.hellofresh.port.in.food.IngredientConstraintUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/food/constrains/")
@AllArgsConstructor
public class IngredientConstrainsController {

    private final IngredientConstraintUseCase ingredientConstraintUseCase;

    @PostMapping("add")
    public ResponseEntity<String> addIngredientConstrain(@RequestParam String ingredient){
        boolean accepted = ingredientConstraintUseCase.addIngredientConstrain(ingredient);

        if (accepted)
            return ResponseEntity.ok().body("Added");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @PostMapping("remove")
    public ResponseEntity<String> removeIngredientConstrain(@RequestParam String ingredient){
        boolean accepted = ingredientConstraintUseCase.removeIngredientConstrain(ingredient);

        if (accepted)
            return ResponseEntity.ok().body("Removed");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

}
