package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.IngredientCommand;
import com.khaledothmane.recipeproject.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    final UnitOfMeasureToUnitOfMeasureCommand converter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.converter = converter;
    }

    @Override
    @Synchronized
    @Nullable
    public IngredientCommand convert(Ingredient source) {

        if (source == null) {
            return null;
        }

        final IngredientCommand target = new IngredientCommand();
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setAmount(source.getAmount());
        target.setUom(converter.convert(source.getUnitOfMeasure()));

        return target;
    }
}
