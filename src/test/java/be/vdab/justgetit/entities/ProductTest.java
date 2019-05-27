package be.vdab.justgetit.entities;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    private Merk merk;
    private Categorie categorie;
    private Subcategorie subcategorie;
    private Product product;

    @Before
    public void before(){
        merk = new Merk(1l,"test", BigDecimal.TEN,
                BigDecimal.TEN);
        categorie = new Categorie(1l,"test");
        subcategorie = new Subcategorie("test",BigDecimal.TEN,
                BigDecimal.TEN, categorie);

        product = new Product(1l,"test",BigDecimal.TEN,
                 BigDecimal.TEN,BigDecimal.TEN,1,0,
                merk,subcategorie);
    }

    @Test
    public void naamVanMerkisCorrectVanProduct(){
        assertEquals("test", product.getMerk().getNaam());
    }

    @Test
    public void naamVanSubcategorieCorrectVanProduct(){
        assertEquals("test",product.getSubcategorie().getNaam());
    }
}
