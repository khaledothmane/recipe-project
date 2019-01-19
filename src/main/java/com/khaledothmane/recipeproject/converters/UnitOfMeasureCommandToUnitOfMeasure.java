package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.UnitOfMeasureCommand;
import com.khaledothmane.recipeproject.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {

        if (source == null) {
            return null;
        }

        final UnitOfMeasure target = new UnitOfMeasure();
        target.setId(source.getId());
        target.setUom(source.getUom());

        return target;
    }
}
