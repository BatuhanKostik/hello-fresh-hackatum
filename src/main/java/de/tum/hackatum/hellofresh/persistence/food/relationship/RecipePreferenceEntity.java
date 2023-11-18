package de.tum.hackatum.hellofresh.persistence.food.relationship;

import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "recipe_preference_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipePreferenceEntity {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private RecipeEntity recipeEntity;
    @ManyToOne
    private UserDetailsEntity userDetailsEntity;
    private int weight;

}
