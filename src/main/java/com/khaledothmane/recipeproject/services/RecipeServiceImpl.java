package com.khaledothmane.recipeproject.services;

import com.khaledothmane.recipeproject.commands.RecipeCommand;
import com.khaledothmane.recipeproject.converters.RecipeCommandToRecipe;
import com.khaledothmane.recipeproject.converters.RecipeToRecipeCommand;
import com.khaledothmane.recipeproject.model.Recipe;
import com.khaledothmane.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service @Slf4j
public class RecipeServiceImpl implements RecipeService {

    final RecipeRepository recipeRepository;
    final RecipeCommandToRecipe recipeCommandToRecipe;
    final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Getting all recipes service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent()) {
            throw new RuntimeException("Recipe Cannot be found");
        }
        return recipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detached_recipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detached_recipe);
        log.debug("Recipe has been saved under Id: " + savedRecipe.getId());
        RecipeCommand returnedRecipe = recipeToRecipeCommand.convert(savedRecipe);
        return returnedRecipe;
    }

    @Override
    public RecipeCommand getRecipeCommandById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent()) {
            throw new RuntimeException("Recipe Cannot be found");
        }
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe.get());
        return recipeCommand;
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent()) {
            throw new RuntimeException("Cannot find Recipe !");
        }
        recipeRepository.deleteById(id);
    }
}
