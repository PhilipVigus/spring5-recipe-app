package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.CategoryCommand;
import com.philvigus.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {
    private final Long CATEGORY_ID = valueOf(1L);
    private final String DESCRIPTION = "description";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void convertNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyCategory() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convertCategory() throws Exception {
        //given
        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(CATEGORY_ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());

    }
}