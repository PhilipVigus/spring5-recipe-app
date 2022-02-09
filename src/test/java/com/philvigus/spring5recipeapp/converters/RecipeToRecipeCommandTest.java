package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.RecipeCommand;
import com.philvigus.spring5recipeapp.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {
    public static final Long RECIPE_ID = Long.valueOf(1L);
    public static final Integer COOKING_TIME = Integer.valueOf(5);
    public static final Integer PREPARATION_TIME = Integer.valueOf(7);
    public static final String DESCRIPTION = "Description";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf(3);
    public static final String SOURCE = "Source";
    public static final String URL = "URL";
    public static final Long FIRST_CATEGORY_ID = Long.valueOf(1L);
    public static final Long SECOND_CATEGORY_ID = Long.valueOf(2L);
    public static final Long FIRST_INGREDIENT_ID = Long.valueOf(3L);
    public static final Long SECOND_INGREDIENT_ID = Long.valueOf(4L);
    public static final Long NOTES_ID = Long.valueOf(9L);

    RecipeToRecipeCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyRecipe() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convertRecipe() throws Exception {
        //given
        Notes notes = new Notes();

        notes.setId(NOTES_ID);

        Recipe recipe = new Recipe();

        recipe.setId(RECIPE_ID);
        recipe.setCookingTime(COOKING_TIME);
        recipe.setPreparationTime(PREPARATION_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        recipe.setNotes(notes);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(FIRST_INGREDIENT_ID);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(SECOND_INGREDIENT_ID);

        Category category = new Category();
        category.setId(FIRST_CATEGORY_ID);

        Category category2 = new Category();
        category2.setId(SECOND_CATEGORY_ID);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOKING_TIME, command.getCookingTime());
        assertEquals(PREPARATION_TIME, command.getPreparationTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());

    }
}