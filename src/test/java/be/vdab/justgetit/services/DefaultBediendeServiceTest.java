package be.vdab.justgetit.services;


import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.repositories.ProductRepository;
import be.vdab.justgetit.repositories.SubcategorieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Import(DefaultBediendeService.class)
//@Sql("/insertCategorie.sql")
//@Sql("/insertSubcategorie.sql")
//@Sql("/insertMerk.sql")
//@Sql("/insertProduct.sql")

public class DefaultBediendeServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DefaultBediendeService bediendeService;

    @Autowired
    private ProductRepository productService;
    @Autowired
    private SubcategorieRepository subcategorieService;

    private Product product;
    private Subcategorie subcategorie;


    private long idVanTestProd() {
//        return super.jdbcTemplate.queryForObject(
//                "select productId from producten where naam = 'testP'", Long.class);
        return super.jdbcTemplate.queryForObject(
                "select productId from producten limit 1", Long.class);

    }

    private long idVanTestSub2() {
//        return super.jdbcTemplate.queryForObject(
//                "select subcategorieId from subcategorieen where naam = 'Test2Subcategorie'", Long.class);
        return super.jdbcTemplate.queryForObject(
                "select subcategorieId from subcategorieen limit 1", Long.class);

    }

    @Before
    public void before() {
        subcategorie = subcategorieService.findById(idVanTestSub2()).get();
    }

    @Test
    public void bediendeServiceWerkt() {
        bediendeService.zetProductInSubCategorie(idVanTestProd(), idVanTestSub2());
        entityManager.flush();
        product = productService.findById(idVanTestProd()).get();
        assertEquals(subcategorie, product.getSubcategorie());
    }

}
