package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.IngredientCommand;
import com.philvigus.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.philvigus.spring5recipeapp.domain.Ingredient;
import com.philvigus.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long INGREDIENT_ID = valueOf(1L);
    public static final Long UNIT_OF_MEASURE_ID = valueOf(2L);

    IngredientCommandToIngredient converter;

    @BeforeEach
    public void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyCommand() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convertCommand() {
        //given
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();

        unitOfMeasureCommand.setId(UNIT_OF_MEASURE_ID);

        IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);

        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());

        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(UNIT_OF_MEASURE_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void convertCommandWithNullUnitOfMeasure() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());

        assertNull(ingredient.getUnitOfMeasure());
    }
}