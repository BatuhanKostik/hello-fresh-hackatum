package de.tum.hackatum.hellofresh.persistence.food;


import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientRepository;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeRepository;
import de.tum.hackatum.hellofresh.persistence.food.relationship.RegionEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class FoodDataLoader {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final RegionRepository regionRepository;
    /*
        TODO Batuhan. Füge die JSON-Datei in die Datenbank hinzu. Hierfür das JSON auslesen und dann
        in die Datenbank mithilfe von save des Repositories. siehe ordner
     */

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        List<IngredientEntity> ingredientEntities = loadIngredients();
        addRecipe(ingredientEntities);
        System.out.println("skadjklasjdljsad");
    }

    private void addRecipe(List<IngredientEntity> ingredientEntities){
         recipeRepository.save(
                RecipeEntity.builder()
                        .name("Foo")
                        .ingredients(new HashSet<>())
                        .link("micros")
                        .workingTimeSeconds(1000)
                        .workingTimeTotalSeconds(2000)
                        .regionEntity(regionEntity)
                        .build()
        );


        save.getIngredients().add(ingredientEntities.get(0));

        recipeRepository.save(save);
    }

    private List<IngredientEntity> loadIngredients() {
        List<IngredientEntity> l = new ArrayList<>();

        l.add(ingredientRepository.save(IngredientEntity.builder()
                        .name("Avocado")
                        .link("www.google.com")
                        .recipes(Set.of())
                .build()));


        l.add(ingredientRepository.save(IngredientEntity.builder()
                .name("Radieschen")
                .link("www.google.com")
                .recipes(Set.of())
                .build()));


        l.add(ingredientRepository.save(IngredientEntity.builder()
                .name("Naturjoghurt")
                .link("www.google.com")
                .recipes(Set.of())
                .build()));


        l.add(ingredientRepository.save(IngredientEntity.builder()
                .name("Rucola")
                .link("www.google.com")
                .recipes(Set.of())
                .build()));

        l.add(ingredientRepository.save(IngredientEntity.builder()
                .name("Pflaumenmus")
                .link("www.google.com")
                .recipes(Set.of())
                .build()));


        return l;
    }



}
