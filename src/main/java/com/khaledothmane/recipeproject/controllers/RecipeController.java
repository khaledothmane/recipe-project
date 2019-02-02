package com.khaledothmane.recipeproject.controllers;

import com.khaledothmane.recipeproject.commands.RecipeCommand;
import com.khaledothmane.recipeproject.converters.RecipeToRecipeCommand;
import com.khaledothmane.recipeproject.model.Difficulty;
import com.khaledothmane.recipeproject.model.Recipe;
import com.khaledothmane.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipe/{id}/show"})
    public String showById(@PathVariable String id, Model model) {
        Recipe recipeById = recipeService.getRecipeById(new Long(id));
        model.addAttribute("recipe", recipeById);
        return "recipe/show";
    }

    @RequestMapping({ "/recipe/new" })
    public String addRecipe(Model model) {
        RecipeCommand recipeAtt = new RecipeCommand();
        recipeAtt.setSource("Author: KHALED Othmane");
        recipeAtt.setDifficulty(Difficulty.MODERATE);
        model.addAttribute("recipe", recipeAtt);
        return "recipe/recipeform";
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        System.out.println(recipeCommand.hashCode());
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

    @RequestMapping("recipe/{id}/edit")
    public String updateRecipe(@PathVariable String id, Model model) {
        RecipeCommand recipe = recipeService.getRecipeCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);
        return "recipe/recipeform";
    }

    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipe(Long.valueOf(id));
        return "redirect:/";
    }
}
