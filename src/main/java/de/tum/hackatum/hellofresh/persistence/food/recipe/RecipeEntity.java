package de.tum.hackatum.hellofresh.persistence.food.recipe;

import de.tum.hackatum.hellofresh.persistence.food.ingredient.IngredientEntity;
import de.tum.hackatum.hellofresh.persistence.food.relationship.RegionEntity;
import de.tum.hackatum.hellofresh.persistence.user.UserDetailsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "recipe_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeEntity {

    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private long id;
    private String name;
    private String link;
    private int workingTimeSeconds;
    private int workingTimeTotalSeconds;

    @ManyToMany
    @JoinTable(
            name = "ingredient_got",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<IngredientEntity> ingredients;

    @OneToOne
    private RegionEntity regionEntity;

    private float kcal;
    private float fat;
    private float carbs;
    private float protein;

}