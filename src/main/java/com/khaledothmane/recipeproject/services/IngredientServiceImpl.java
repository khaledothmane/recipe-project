package com.khaledothmane.recipeproject.services;

import com.khaledothmane.recipeproject.commands.IngredientCommand;
import com.khaledothmane.recipeproject.converters.IngredientCommandToIngredient;
import com.khaledothmane.recipeproject.converters.IngredientToIngredientCommand;
import com.khaledothmane.recipeproject.model.Ingredient;
import com.khaledothmane.recipeproject.model.Recipe;
import com.khaledothmane.recipeproject.repositories.IngredientRepository;
import com.khaledothmane.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    final RecipeRepository recipeRepository;
    final IngredientToIngredientCommand converter;
    final IngredientCommandToIngredient _converter;
    final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand converter, IngredientCommandToIngredient _converter, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.converter = converter;
        this._converter = _converter;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientCommand getIngredientByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (!optionalRecipe.isPresent()) {
            throw new RuntimeException("Cannot find Recipe by ID: " + recipeId);
        }
        log.info("Getting ingredient for Recipe by ID: ", recipeId);
        Optional<Ingredient> optionalIngredient = optionalRecipe.get().getIngredients().stream()
                .filter(ingredient -> ingredient.getId() == ingredientId)
                .findFirst();
        if (!optionalIngredient.isPresent()) {
            log.error("Cannot get ingredient for Recipe by ID: ", recipeId);
            return null;
        }
        return converter.convert(optionalIngredient.get());
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(ingredientCommand.getRecipeId());
        if (!optionalRecipe.isPresent()) {
            log.error("Cannot find recipe with ID: ", ingredientCommand.getRecipeId());
            return new IngredientCommand();
        } else {
            Ingredient saved = ingredientRepository.save(_converter.convert(ingredientCommand));
            return converter.convert(saved);
        }
    }
}
