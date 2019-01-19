package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.RecipeCommand;
import com.khaledothmane.recipeproject.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;


public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    final CategoryToCategoryCommand categoryConverter;
    final IngredientToIngredientCommand ingredientConverter;
    final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {

        if (source == null) return null;

        RecipeCommand target = new RecipeCommand();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setPrepTime(source.getPrepTime());
        target.setCookTime(source.getCookTime());
        target.setServings(source.getServings());
        target.setSource(source.getSource());
        target.setUrl(source.getUrl());
        target.setDirections(source.getDirections());
        if (!source.getCategories().isEmpty()) {
            source.getCategories().forEach(category -> {
                target.getCategories().add(categoryConverter.convert(category));
            });
        }
        target.setNotes(notesConverter.convert(source.getNotes()));
        if (!source.getIngredients().isEmpty()) {
            source.getIngredients().forEach(ingredient -> {
                target.getIngredients().add(ingredientConverter.convert(ingredient));
            });
        }

        return null;
    }
}
