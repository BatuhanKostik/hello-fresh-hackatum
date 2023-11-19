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
        loadIngredients();
        addRecipe();
    }

    private IngredientEntity byName(String name) {
        return ingredientRepository.getIngredientEntityByName(name).get();
    }

    private void addRecipe(){
        RecipeEntity r1 = recipeRepository.save(
                RecipeEntity.builder()
                        .name("Tacos mit Räuchertofu & Mango-Paprika-Salsa")
                        .ingredients(new HashSet<>())
                        .link("https://img.hellofresh.com/c_fit,f_auto,fl_lossy,h_1100,q_30,w_2600/hellofresh_s3/image/HF_Y23_R33_W05_DE_R4484-1_KB_Main_low-99964e18.jpg")
                        .content("https://www.hellofresh.de/recipes/couscous-mit-dukkah-gemuse-and-hirtenkase-thermomix-650819fe270f68660e1ac70b")
                        .workingTimeSeconds(20 * 60)
                        .workingTimeTotalSeconds(30 * 60)
                        .regionEntity(RegionEntity.builder().name("Mexikanisch").build())
                        .kcal(878)
                        .carbs(6.2f)
                        .protein(32.1f)
                        .fat(18f)
                        .build()
        );

        r1.getIngredients().add(byName("Weizentortillas"));
        r1.getIngredients().add(byName("geräucherter Tofu"));
        r1.getIngredients().add(byName("Mango"));
        r1.getIngredients().add(byName("rote Spitzpaprika"));
        r1.getIngredients().add(byName("rote Zwiebel"));
        r1.getIngredients().add(byName("Gewürzmischung Hello Harissa"));
        r1.getIngredients().add(byName("Sojasoße"));
        r1.getIngredients().add(byName("vegane Mayonnaise"));
        r1.getIngredients().add(byName("Knoblauchzehe"));
        r1.getIngredients().add(byName("Olivenöl"));
        r1.getIngredients().add(byName("Salz"));
        r1.getIngredients().add(byName("Zucker"));
        r1.getIngredients().add(byName("Weißweinessig"));
        r1.getIngredients().add(byName("Pfeffer"));
        r1.getIngredients().add(byName("Öl"));

        recipeRepository.save(r1);


        RecipeEntity r2 = recipeRepository.save(
                RecipeEntity.builder()
                        .name("Tacos mit Räuchertofu & Mango-Paprika-Salsa")
                        .ingredients(new HashSet<>())
                        .link("https://img.hellofresh.com/c_fit,f_auto,fl_lossy,h_1100,q_30,w_2600/hellofresh_s3/image/HF_Y23_R10_W43_DE_PM4758-1_Main_F2_low-74af93fa.jpg")
                        .content("https://www.hellofresh.de/recipes/couscous-mit-dukkah-gemuse-and-hirtenkase-thermomix-650819fe270f68660e1ac70b")
                        .workingTimeSeconds(20 * 60)
                        .workingTimeTotalSeconds(30 * 60)
                        .regionEntity(RegionEntity.builder().name("Deutsch").build())
                        .kcal(1211)
                        .carbs(15.2f)
                        .protein(25.4f)
                        .fat(18f)
                        .build()
        );

        r2.getIngredients().add(byName("würziger Gouda, gerieben"));
        r2.getIngredients().add(byName("Mini-Fladenbrot"));
        r2.getIngredients().add(byName("Jalapeno"));
        r2.getIngredients().add(byName("Avocado"));
        r2.getIngredients().add(byName("Tomate"));
        r2.getIngredients().add(byName("Aioli"));
        r2.getIngredients().add(byName("Limette, gewachst"));
        r2.getIngredients().add(byName("Koriander"));
        r2.getIngredients().add(byName("Ketchup"));
        r2.getIngredients().add(byName("Sükartoffel"));
        r2.getIngredients().add(byName("Gewürzmischung Hello Fiesta"));
        r2.getIngredients().add(byName("Pulled Pork"));
        r2.getIngredients().add(byName("Öl"));
        r2.getIngredients().add(byName("Zucker"));
        r2.getIngredients().add(byName("Pfeffer"));
        r2.getIngredients().add(byName("Wasser"));
        r2.getIngredients().add(byName("Salz"));

        recipeRepository.save(r2);


        RecipeEntity r3 = recipeRepository.save(
                RecipeEntity.builder()
                        .name("Tacos mit Räuchertofu & Mango-Paprika-Salsa")
                        .ingredients(new HashSet<>())
                        .link("https://img.hellofresh.com/c_fit,f_auto,fl_lossy,h_1100,q_30,w_2600/hellofresh_s3/image/HF_Y23_R10_W43_DE_PM4758-1_Main_F2_low-74af93fa.jpg")
                        .content("https://www.hellofresh.de/recipes/couscous-mit-dukkah-gemuse-and-hirtenkase-thermomix-650819fe270f68660e1ac70b")
                        .workingTimeSeconds(20 * 60)
                        .workingTimeTotalSeconds(30 * 60)
                        .regionEntity(RegionEntity.builder().name("Deutsch").build())
                        .kcal(1211)
                        .carbs(15.2f)
                        .protein(25.4f)
                        .fat(18f)
                        .build()
        );

        r3.getIngredients().add(byName("Weizentortillas"));
        r3.getIngredients().add(byName("rote Spitzpaprika"));
        r3.getIngredients().add(byName("Jalapeno"));
        r3.getIngredients().add(byName("würziger Gouda, gerieben"));
        r3.getIngredients().add(byName("Tomate"));
        r3.getIngredients().add(byName("Aioli"));


        recipeRepository.save(r3);


    }

    private List<IngredientEntity> loadIngredients() {

        return null;
    }



}
