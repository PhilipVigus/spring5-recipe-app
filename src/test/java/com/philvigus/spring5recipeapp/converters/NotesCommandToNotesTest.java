package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.NotesCommand;
import com.philvigus.spring5recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    public static final Long NOTE_ID = valueOf(1L);
    public static final String CONTENT = "Content";

    NotesCommandToNotes converter;

    @BeforeEach
    public void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyCommand() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convertCommand() {
        //given
        NotesCommand notesCommand = new NotesCommand();

        notesCommand.setId(NOTE_ID);
        notesCommand.setContent(CONTENT);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(NOTE_ID, notes.getId());
        assertEquals(CONTENT, notes.getContent());
    }
}