package de.tum.hackatum.hellofresh.port.out;

import org.springframework.security.core.userdetails.UserDetails;

public interface RelationshipPort {

    boolean addIngredientSuggestion(UserDetails user, String name, int weight);

    boolean addRecipeSuggestion(UserDetails principal, String name, int weight);

}