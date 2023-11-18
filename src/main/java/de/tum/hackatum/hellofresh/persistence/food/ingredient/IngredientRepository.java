package de.tum.hackatum.hellofresh.persistence.food.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    Optional<IngredientEntity> getIngredientEntityByName(String name);

}
