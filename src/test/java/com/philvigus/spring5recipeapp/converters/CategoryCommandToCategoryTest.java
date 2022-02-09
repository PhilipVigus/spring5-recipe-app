package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.CategoryCommand;
import com.philvigus.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {
    private final Long CATEGORY_ID = valueOf(1L);
    private final String DESCRIPTION = "description";

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyCommand()  {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convertCommand() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(CATEGORY_ID);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertEquals(CATEGORY_ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}