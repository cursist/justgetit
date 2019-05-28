package be.vdab.justgetit.forms;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MargeWijzigingTest {

    private Validator validator;


    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void idMagNietNullZijn() {
        assertFalse(validator.validateValue(MargeWijziging.class,
                "id", null).isEmpty());
    }


    @Test
    public void minimumMargePercentMagNietNegatiefZijn() {
        assertFalse(validator.validateValue(MargeWijziging.class,
                "minimumMargePercent", BigDecimal.valueOf(-1)).isEmpty());
    }


    @Test
    public void minimumMargeBedragMagNietNegatiefZijn() {
        assertFalse(validator.validateValue(MargeWijziging.class,
                "minimumMargeBedrag", BigDecimal.valueOf(-1)).isEmpty());
    }


}