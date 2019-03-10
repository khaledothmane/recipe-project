package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.IngredientCommand;
import com.khaledothmane.recipeproject.model.Ingredient;
import com.khaledothmane.recipeproject.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    final UnitOfMeasureCommandToUnitOfMeasure converter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure converter) {
        this.converter = converter;
    }

    @Override
    @Synchronized
    @Nullable
    public Ingredient convert(IngredientCommand source) {

        if (source == null) {
            return null;
        }

        final Ingredient target = new Ingredient();
        if(source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            target.setRecipe(recipe);
        }
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setAmount(source.getAmount());
        target.setUnitOfMeasure(converter.convert(source.getUnitOfMeasureCommand()));

        return target;
    }
}
