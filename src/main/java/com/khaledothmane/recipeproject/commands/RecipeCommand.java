package com.khaledothmane.recipeproject.commands;

import com.khaledothmane.recipeproject.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<CategoryCommand> categories = new HashSet<>();
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();


}
