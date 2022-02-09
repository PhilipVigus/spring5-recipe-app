package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.IngredientCommand;
import com.philvigus.spring5recipeapp.domain.Ingredient;
import com.philvigus.spring5recipeapp.domain.Recipe;
import com.philvigus.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long UNIT_OF_MEASURE_ID = valueOf(2L);
    public static final Long INGREDIENT_ID = valueOf(1L);

    IngredientToIngredientCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyIngredient() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convertIngredient() {
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();

        unitOfMeasure.setId(UNIT_OF_MEASURE_ID);

        Ingredient ingredient = new Ingredient();

        ingredient.setId(INGREDIENT_ID);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertEquals(INGREDIENT_ID, ingredientCommand.getId());
        assertEquals(UNIT_OF_MEASURE_ID, ingredientCommand.getUnitOfMeasure().getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());

        assertNotNull(ingredientCommand.getUnitOfMeasure());
    }
    @Test
    public void convertIngredientWithNullUnitOfMeasure() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();

        ingredient.setId(INGREDIENT_ID);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(INGREDIENT_ID, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }
}