package de.tum.hackatum.hellofresh.persistence.food.relationship;

import de.tum.hackatum.hellofresh.persistence.food.recipe.RecipeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "region_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionEntity {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    private RecipeEntity recipeEntity;

}
