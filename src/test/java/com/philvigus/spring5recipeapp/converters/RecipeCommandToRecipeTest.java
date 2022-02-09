package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.CategoryCommand;
import com.philvigus.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {
    private final Long RECIPE_ID = valueOf(1L);
    private final String DESCRIPTION = "description";

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void convertNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyCommand() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convertCommand() throws Exception {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(RECIPE_ID);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertEquals(RECIPE_ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}