package com.khaledothmane.recipeproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    // Cascading isn't necessary here. Deleting notes shouldn't delete the recipe.
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
