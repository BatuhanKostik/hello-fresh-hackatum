package de.tum.hackatum.hellofresh.service.suggestion;


import de.tum.hackatum.hellofresh.port.in.food.IngredientConstraintUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class IngredientService implements IngredientConstraintUseCase {

    private final IngredientConstraintPort ingredientConstraintPort;

    @Override
    public boolean addIngredientConstrain(String ingredient) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            log.error("Tried to add ingredient constrains " + ingredient + ", but the user dont exist.");
            return false;
        }

        log.info("Tried to add ingredient constrains " + ingredient + " for user " + principal.getUsername());

        return ingredientConstraintPort.addIngredientConstraint(principal, ingredient);
    }

    @Override
    public boolean removeIngredientConstrain(String ingredient) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            log.error("Tried to remove ingredient constrains " + ingredient + ", but the user dont exist.");
            return false;
        }

        log.info("Tried to remove ingredient constrains " + ingredient + " for user " + principal.getUsername());

        return ingredientConstraintPort.removeIngredientConstraint(principal, ingredient);
    }

}
