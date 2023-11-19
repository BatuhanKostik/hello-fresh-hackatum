package de.tum.hackatum.hellofresh.service.suggestion;

import org.springframework.security.core.userdetails.UserDetails;

public interface IngredientConstraintPort {

    boolean addIngredientConstraint(UserDetails userDetails, String ingredient);

    boolean removeIngredientConstraint(UserDetails userDetails, String ingredient);

}
