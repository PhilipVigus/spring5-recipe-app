package com.philvigus.spring5recipeapp.converters;

import com.philvigus.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.philvigus.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {
    public static final String DESCRIPTION = "description";
    public static final Long UNIT_OF_MEASURE_ID = valueOf(1L);

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyUnitOfMeasure() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convertUnitOfMeasure() {
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();

        unitOfMeasure.setId(UNIT_OF_MEASURE_ID);
        unitOfMeasure.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        //then
        assertEquals(UNIT_OF_MEASURE_ID, unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}