package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.philvigus.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {
    public static final String DESCRIPTION = "description";
    public static final Long UNIT_OF_MEASURE_ID = valueOf(1L);

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    public void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyCommand() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convertCommand() {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();

        command.setId(UNIT_OF_MEASURE_ID);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure unitOfMeasure = converter.convert(command);

        //then
        assertNotNull(unitOfMeasure);
        assertEquals(UNIT_OF_MEASURE_ID, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());

    }
}