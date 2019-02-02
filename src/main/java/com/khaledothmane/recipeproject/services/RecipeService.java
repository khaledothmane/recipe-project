package com.khaledothmane.recipeproject.services;

import com.khaledothmane.recipeproject.commands.RecipeCommand;
import com.khaledothmane.recipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand getRecipeCommandById(Long id);
    void deleteRecipe(Long id);
}
