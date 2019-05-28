package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/insertCategorie.sql")
@Sql("/insertSubcategorie.sql")
@Sql("/insertMerk.sql")
@Sql("/insertProduct.sql")
public class ProductRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String PRODUCTEN = "producten";
    private Merk merk;
    private Categorie categorie;
    private Subcategorie subcategorie;
    private Product product;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager manager;

    private long idVanTestProduct(){
        return super.jdbcTemplate.queryForObject("select productId from producten where naam='testP'",Long.class);
    }

    @Before
    public void before(){
        merk = new Merk(1L,"testM", BigDecimal.TEN, BigDecimal.TEN);
        categorie = new Categorie("testC");
        subcategorie = new Subcategorie("testSC", categorie);
        product = new Product("testP",BigDecimal.ONE,
                BigDecimal.TEN,BigDecimal.TEN,1,0, merk,subcategorie);
    }

    @Test
    public void findByVerkoopprijsBetween(){

        List<Product> producten = productRepository.findByVerkoopprijsBetween(BigDecimal.ONE,BigDecimal.TEN);
        assertEquals(super.countRowsInTableWhere(PRODUCTEN,"verkoopprijs between 1 and 10"),producten.size());
        producten.stream().map(product1 -> product.getVerkoopprijs())
                .reduce((vorigePrijs,prijs)->{
            assertTrue(prijs.compareTo(BigDecimal.ONE)>= 0);
            assertTrue(prijs.compareTo(BigDecimal.TEN)<= 0);
            assertTrue(prijs.compareTo(vorigePrijs)>=0);
            return prijs;
        });
    }

    @Test
    public void findByBesteldIsNull(){
        List<Product> producten = productRepository.findByBesteldIsNull();
        assertEquals(0,producten.size());
    }

    @Test
    public void findByVoorraadLessThan(){
        List<Product> producten = productRepository.findByVoorraadLessThan(15);
        assertEquals(1,producten.size());
    }


    @Test
    public void findByNaamContaining(){
        List<Product> producten = productRepository.findByNaamContaining("test");
        assertEquals(1,producten.size());
    }
}