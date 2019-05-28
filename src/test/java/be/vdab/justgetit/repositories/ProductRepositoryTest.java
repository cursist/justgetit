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
        return super.jdbcTemplate.queryForObject("select productId from producten where naam='testP'",long.class);
    }

    @Before
    public void before(){
        merk = new Merk(1L,"testM", BigDecimal.TEN, BigDecimal.TEN);
        categorie = new Categorie("testC");
        subcategorie = new Subcategorie("testSC", categorie);
        product = new Product("testP",BigDecimal.TEN,
                BigDecimal.TEN,BigDecimal.valueOf(15),1,0, merk,subcategorie);
    }

    @Test
    public void findByVerkoopprijsBetween(){
        List<Product> producten = productRepository.findByVerkoopprijsBetween(BigDecimal.TEN,BigDecimal.valueOf(15));

    }




}