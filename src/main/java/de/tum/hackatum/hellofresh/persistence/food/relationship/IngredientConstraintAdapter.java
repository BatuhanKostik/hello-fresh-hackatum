package de.tum.hackatum.hellofresh.persistence.food.relationship;

import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientRepository;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsMapper;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsRepository;
import de.tum.hackatum.hellofresh.service.suggestion.IngredientConstraintPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class IngredientConstraintAdapter implements IngredientConstraintPort {

    private final IngredientConstrainRepository ingredientConstrainRepository;
    private final IngredientRepository ingredientRepository;
    private final UserDetailsMapper userDetailsMapper;
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public boolean addIngredientConstraint(UserDetails userDetails, String ingredient) {
        Optional<IngredientEntity> optionalIngredient = ingredientRepository.getIngredientEntityByName(ingredient);

        if (optionalIngredient.isEmpty()) {
            log.info("The ingredient " + ingredient + " was not found.");
            return false;
        }

        IngredientEntity ingredientEntity = optionalIngredient.get();

        Optional<UserDetailsEntity> byUsername = userDetailsRepository.findByUsername(userDetails.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }


        IngredientConstraintEntity entity = IngredientConstraintEntity.builder()
                .ingredientEntity(ingredientEntity)
                .userDetailsEntity(byUsername.get())
                .build();

        ingredientConstrainRepository.save(entity);

        return true;
    }

    @Override
    public boolean removeIngredientConstraint(UserDetails userDetails, String ingredient) {
        Optional<UserDetailsEntity> byUsername = userDetailsRepository.findByUsername(userDetails.getUsername());

        if (byUsername.isEmpty()) {
            log.info("Could not found user by username");
            return false;
        }

        UserDetailsEntity details = byUsername.get();
        Optional<IngredientEntity> optionalIngredient = ingredientRepository.getIngredientEntityByName(ingredient);

        if (optionalIngredient.isEmpty()) {
            log.info("Could not find ingredient.");
            return false;
        }

        IngredientEntity ingredientEntity = optionalIngredient.get();

        ingredientConstrainRepository.deleteByIngredientEntityAndUserDetailsEntity(ingredientEntity, details);

        return true;
    }

}
