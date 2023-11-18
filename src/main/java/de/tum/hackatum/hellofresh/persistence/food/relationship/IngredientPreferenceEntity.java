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
@Table(name = "ingredient_preference_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientPreferenceEntity {

    @Id
    @GeneratedValue
    private long id;

    private int weight;

    @ManyToOne
    private IngredientEntity ingredientEntity;

    @ManyToOne
    private UserDetailsEntity userDetailsEntity;

}
