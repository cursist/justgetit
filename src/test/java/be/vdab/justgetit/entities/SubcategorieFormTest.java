package be.vdab.justgetit.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.*;

public class SubcategorieFormTest {

    private Validator validator;

    @Before
    public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void categorieVanSubcategorieMagNietNullzijn(){
        assertFalse(
                validator.validateValue(
                        Subcategorie.class, "categorie", null
                ).isEmpty()
        );
    }

    @Test
    public void categorieVanSubcategorieIsOk(){
        assertTrue(
                validator.validateValue(
                        Subcategorie.class, "categorie", new Categorie("categorie")
                ).isEmpty()
        );
    }

    @Test
    public void naamOk() {
        assertTrue(
                validator.validateValue(
                        Subcategorie.class,
                        "naam",
                        "categorieNaam"
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietNullZijn() {
        assertFalse(
                validator.validateValue(
                        Subcategorie.class,
                        "naam",
                        null
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietLeegZijn() {
        assertFalse(
                validator.validateValue(
                        Subcategorie.class,
                        "naam",
                        ""
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietEnkelSpatiesBevatten() {
        assertFalse(
                validator.validateValue(
                        Subcategorie.class,
                        "naam",
                        "   "
                ).isEmpty()
        );
    }





}
