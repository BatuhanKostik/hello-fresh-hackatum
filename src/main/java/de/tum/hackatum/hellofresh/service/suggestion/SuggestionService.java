package de.tum.hackatum.hellofresh.service.suggestion;

import de.tum.hackatum.hellofresh.port.in.suggestion.InitialSuggestionUseCase;
import de.tum.hackatum.hellofresh.port.in.suggestion.SuggestionUseCase;
import de.tum.hackatum.hellofresh.port.out.RelationshipPort;
import de.tum.hackatum.hellofresh.web.suggestion.IngredientResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SuggestionService implements SuggestionUseCase, InitialSuggestionUseCase {

    private final RelationshipPort relationshipPort;

    @Override
    public boolean addIngredientSuggestion(String name, int weight) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            log.error("Tried to add ingredient suggestion for " + name + " with a weight of " + weight + ", but the user dont exist.");
            return false;
        }

        log.info("Add new ingredient suggestion for " + name + " with a weight of " + weight + " for the user " + principal.getUsername());


        return relationshipPort.addIngredientSuggestion(principal, name, weight);
    }

    @Override
    public boolean addRecipeSuggestion(String name, int weight) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal == null) {
            log.error("Tried to add ingredient suggestion for " + name + " with a weight of " + weight + ", but the user dont exist.");
            return false;
        }

        log.info("Add new ingredient suggestion for " + name + " with a weight of " + weight + " for the user " + principal.getUsername());

        return relationshipPort.addRecipeSuggestion(principal, name, weight);
    }

    @Override
    public List<IngredientResult> getIngredientSuggestion() {
        //TODO add also here,
        return List.of(
                new IngredientResult("Tomato", "www.google.com")
        );
    }



}
