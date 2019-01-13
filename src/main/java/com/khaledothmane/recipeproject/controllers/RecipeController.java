package com.khaledothmane.recipeproject.controllers;

import com.khaledothmane.recipeproject.model.Recipe;
import com.khaledothmane.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipe/show/{id}"})
    public String showById(@PathVariable String id, Model model) {
        Recipe recipeById = recipeService.getRecipeById(new Long(id));
        model.addAttribute("recipe", recipeById);
        return "/recipe/show";
    }
}
