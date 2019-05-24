package be.vdab.justgetit.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CategorieDomainTest {
    private Categorie categorie1, categorie2, categorie3;

    @Before
    public void before() {
        categorie1 = new Categorie( "Computers");
        categorie2 = new Categorie( "Kleding");
        categorie3 = new Categorie( "Kleding");
    }

    @Test
    public void categorie1IsNietGelijkAancategorie2() {
        assertNotEquals(categorie1, categorie2);
    }

    @Test
    public void categorie2IsGelijkAancategorie3() {
        assertEquals(categorie2, categorie3);
    }

    @Test
    public void categorie1VerschiltVanNull() {
        assertNotEquals(categorie1, null);
    }

    @Test
    public void categorie1VerschiltVanEenAnderTypeObject() {
        assertNotEquals(categorie1, "");
    }

    @Test
    public void categorie2EnCategorie3GevenDezelfdeHashCode() {
        assertEquals(categorie2, categorie3);
    }

    @Test
    public void categorie1EnCategorie2GevenEenAndereHashCode() {
        assertNotEquals(categorie1, categorie2);
    }
}
