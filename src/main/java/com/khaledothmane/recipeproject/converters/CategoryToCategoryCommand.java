package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.CategoryCommand;
import com.khaledothmane.recipeproject.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Override
    @Synchronized
    @Nullable
    public CategoryCommand convert(Category source) {

        if (source == null) {
            return null;
        }

        final CategoryCommand target = new CategoryCommand();
        target.setId(source.getId());
        target.setName(source.getName());

        return target;
    }
}
