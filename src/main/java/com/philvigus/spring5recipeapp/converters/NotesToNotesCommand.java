package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.NotesCommand;
import com.philvigus.spring5recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes == null) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();

        notesCommand.setId(notes.getId());
        notesCommand.setContent(notes.getContent());

        return notesCommand;
    }
}
