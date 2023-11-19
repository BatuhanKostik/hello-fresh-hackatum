package de.tum.hackatum.hellofresh.persistence.food.relationship;

import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientConstrainRepository extends JpaRepository<IngredientConstraintEntity, Long> {

    void deleteByIngredientEntityAndUserDetailsEntity(IngredientEntity entity, UserDetailsEntity userDetails);

    List<IngredientConstraintEntity> findAllByUserDetailsEntity(UserDetailsEntity userDetailsEntity);

}
