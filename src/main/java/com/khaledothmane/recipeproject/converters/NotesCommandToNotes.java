package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.NotesCommand;
import com.khaledothmane.recipeproject.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {

        if (source == null) {
            return null;
        }

        final Notes target = new Notes();
        target.setId(source.getId());
        target.setRecipeNotes(source.getRecipeNotes());

        return target;
    }
}
