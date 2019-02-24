package com.khaledothmane.recipeproject.controllers;

import com.khaledothmane.recipeproject.commands.IngredientCommand;
import com.khaledothmane.recipeproject.commands.RecipeCommand;
import com.khaledothmane.recipeproject.model.Recipe;
import com.khaledothmane.recipeproject.services.IngredientService;
import com.khaledothmane.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IngredientController {

    final RecipeService recipeService;
    final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping({"/recipe/{recipeId}/ingredients"})
    public String listIngredients(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.getRecipeCommandById(Long.valueOf(recipeId));
        log.debug("Getting ingredients for Recipe: " + recipeCommand.getId() + " ID");
        model.addAttribute("recipe", recipeCommand);

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipe_id}/ingredient/{ingredient_id}/show")
    public String showIngredient(@PathVariable("recipe_id") String recipe_id,
                                 @PathVariable("ingredient_id") String ingredient_id, Model model) {

        IngredientCommand ingredientCommand = ingredientService.getIngredientByRecipeIdAndIngredientId(
                Long.valueOf(recipe_id), Long.valueOf(ingredient_id)
        );
        model.addAttribute("ingredient", ingredientCommand);

        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipe_id}/ingredient/{ingredient_id}/edit")
    public String showIngredientForm(@PathVariable("recipe_id") String recipe_id,
                                     @PathVariable("ingredient_id") String ingredient_id, Model model) {
        IngredientCommand ingredientCommand = ingredientService.getIngredientByRecipeIdAndIngredientId(
                Long.valueOf(recipe_id), Long.valueOf(ingredient_id)
        );
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredient/ingredientform";
    }

}
