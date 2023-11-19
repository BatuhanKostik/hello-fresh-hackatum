package de.tum.hackatum.hellofresh.persistence.food.relationship;

import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ingredient_constraint_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientConstraintEntity {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private IngredientEntity ingredientEntity;
    @OneToOne
    private UserDetailsEntity userDetailsEntity;

}
