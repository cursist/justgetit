package be.vdab.justgetit.entities;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SubcategorieTest {
    private Categorie categorie;
    private Subcategorie subcategorie;

    @Before
    public void before(){
        categorie = new Categorie(1L, "test");
        subcategorie = new Subcategorie( "test", BigDecimal.TEN,
                BigDecimal.TEN, categorie);
    }
    @Test
    public void naamVanCategorieVanSubcategorieIsTest(){
        assertEquals("test", subcategorie.getCategorie().getNaam());
    }


}
