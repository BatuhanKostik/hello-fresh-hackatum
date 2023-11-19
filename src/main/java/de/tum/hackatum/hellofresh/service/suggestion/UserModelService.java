package de.tum.hackatum.hellofresh.service.suggestion;

import de.tum.hackatum.hellofresh.persistence.food.relationship.IngredientPreferenceEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.RecipePreferenceEntity;
import de.tum.hackatum.hellofresh.port.in.food.RecipeResponse;
import de.tum.hackatum.hellofresh.service.suggestion.model.AthleteModelStrategy;
import de.tum.hackatum.hellofresh.service.suggestion.model.ModelStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserModelService {

    public ModelStrategy getModel(String model,
                                  List<RecipePreferenceEntity> preferenceRecipe,
                                  List<IngredientPreferenceEntity> preferenceIngredient,
                                  List<RecipeResponse> alreadySelected) {
        if (model.equals("Athlete"))
            return new AthleteModelStrategy(preferenceRecipe, preferenceIngredient, alreadySelected);

        return null;
    }


}
