package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.RecipeCommand;
import com.khaledothmane.recipeproject.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    final CategoryCommandToCategory catConverter;
    final IngredientCommandToIngredient ingConverter;
    final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory catConverter, IngredientCommandToIngredient ingConverter, NotesCommandToNotes notesConverter) {
        this.catConverter = catConverter;
        this.ingConverter = ingConverter;
        this.notesConverter = notesConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {

        if (source == null) {
            return null;
        }

        final Recipe target = new Recipe();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setPrepTime(source.getPrepTime());
        target.setCookTime(source.getCookTime());
        target.setServings(source.getServings());
        target.setSource(source.getSource());
        target.setUrl(source.getUrl());
        target.setDifficulty(source.getDifficulty());
        target.setDirections(source.getDirections());
        if (!source.getCategories().isEmpty()) {
            source.getCategories().forEach(category -> {
                target.getCategories().add(catConverter.convert(category));
            });
        }
        target.setNotes(notesConverter.convert(source.getNotes()));
        if (!source.getIngredients().isEmpty()) {
            source.getIngredients().forEach(ingredient -> {
                target.getIngredients().add(ingConverter.convert(ingredient));
            });
        }

        return target;
    }
}
