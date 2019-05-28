package be.vdab.justgetit.entities;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

import javax.validation.Validator;

public class SubcategorieEigenschapFormTest {

    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void subcategorieVanSubcategorieEigenschapMagNietNullzijn(){
        assertFalse(
                validator.validateValue(
                        SubcategorieEigenschap.class, "subcategorie", null
                ).isEmpty()
        );
    }

    @Test
    public void categorieVanSubcategorieEigenschapIsOk(){
        assertTrue(
                validator.validateValue(
                        SubcategorieEigenschap.class, "subcategorie", new Categorie("categorie")
                ).isEmpty()
        );
    }

    @Test
    public void naamOk() {
        assertTrue(
                validator.validateValue(
                        SubcategorieEigenschap.class,
                        "naam",
                        "categorieNaam"
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietNullZijn() {
        assertFalse(
                validator.validateValue(
                        SubcategorieEigenschap.class,
                        "naam",
                        null
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietLeegZijn() {
        assertFalse(
                validator.validateValue(
                        SubcategorieEigenschap.class,
                        "naam",
                        ""
                ).isEmpty()
        );
    }

    @Test
    public void naamMagNietEnkelSpatiesBevatten() {
        assertFalse(
                validator.validateValue(
                        SubcategorieEigenschap.class,
                        "naam",
                        "   "
                ).isEmpty()
        );
    }


}
