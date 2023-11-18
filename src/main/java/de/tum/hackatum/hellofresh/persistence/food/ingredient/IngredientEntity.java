package de.tum.hackatum.hellofresh.persistence.food.ingredient;

import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "ingredient_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientEntity {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private long id;
    private String name;
    private String link;
    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeEntity> recipes;

}