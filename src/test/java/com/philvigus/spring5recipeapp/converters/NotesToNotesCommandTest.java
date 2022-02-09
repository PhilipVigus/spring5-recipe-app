package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.NotesCommand;
import com.philvigus.spring5recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotesToNotesCommandTest {
    public static final Long NOTE_ID = valueOf(1L);
    public static final String CONTENT = "Content";

    NotesToNotesCommand converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void convertNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyNote() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convertNote() throws Exception {
        //given
        Notes notes = new Notes();

        notes.setId(NOTE_ID);
        notes.setContent(CONTENT);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertEquals(NOTE_ID, notesCommand.getId());
        assertEquals(CONTENT, notesCommand.getContent());
    }
}