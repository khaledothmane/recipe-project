package com.khaledothmane.recipeproject.converters;

import com.khaledothmane.recipeproject.commands.NotesCommand;
import com.khaledothmane.recipeproject.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {

        if (source == null) {
            return null;
        }

        final NotesCommand target = new NotesCommand();
        target.setId(source.getId());
        target.setRecipeNotes(source.getRecipeNotes());

        return target;
    }
}
