package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.UnitOfMeasureCommand;
import com.khaledothmane.recipeproject.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Nullable
    @Synchronized
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {

        if (source == null) {
            return null;
        }

        final UnitOfMeasureCommand target = new UnitOfMeasureCommand();
        target.setId(source.getId());
        target.setUom(source.getUom());

        return target;
    }
}
