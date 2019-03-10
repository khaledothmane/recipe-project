package com.khaledothmane.recipeproject.services;

import com.khaledothmane.recipeproject.commands.IngredientCommand;
import com.khaledothmane.recipeproject.model.Ingredient;

public interface IngredientService {

    public IngredientCommand getIngredientByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    public void deleteIngredient(Long ingredientId);
}
