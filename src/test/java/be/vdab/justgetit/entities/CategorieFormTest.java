package be.vdab.justgetit.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

public class CategorieFormTest {
    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void naamOk() {
        assertTrue(
                validator.validateValue(
                        Categorie.class,
                        "naam",
                        "categorieNaam"
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietNullZijn() {
        assertFalse(
                validator.validateValue(
                        Categorie.class,
                        "naam",
                        null
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietLeegZijn() {
        assertFalse(
                validator.validateValue(
                        Categorie.class,
                        "naam",
                        ""
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietEnkelSpatiesBevatten() {
        assertFalse(
                validator.validateValue(
                        Categorie.class,
                        "naam",
                        "   "
                ).isEmpty()
        );
    }
}
