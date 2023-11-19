package de.tum.hackatum.hellofresh.persistence.food.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    Optional<RecipeEntity> getRecipeEntityByName(String name);


}
