package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import(DefaultBediendeService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@Sql("/insertCategorie.sql")
@Sql("/insertSubcategorie.sql")
@Sql("/insertMerk.sql")
@Sql("/insertProduct.sql")
public class DefaultBediendeServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private DefaultBediendeService service;
    @Autowired
    private EntityManager manager;

    private long idVanTestProduct(){
        return super.jdbcTemplate.queryForObject("select productId from producten where naam='testP'",long.class);
    }

    private long subcategorieIdVanTestSubcategorie(){
        return super.jdbcTemplate.queryForObject("select subcategorieId from subcategorieen where naam = 'TestSubcategorie'",long.class);
    }

    private long subcategorieIdVanTestProduct(){
        return super.jdbcTemplate.queryForObject("select subcategorieId from producten where naam = 'testP'",long.class);
    }

    private long minimaleVerkoopprijsVanProduct(){
        return super.jdbcTemplate.queryForObject("select minimumprijs from producten where naam = 'testP'",long.class);
    }

    @Test
    public void zetProductInSubcategorie(){
        service.zetProductInSubCategorie(idVanTestProduct(),subcategorieIdVanTestSubcategorie());
        manager.flush();
        assertEquals(subcategorieIdVanTestProduct(),subcategorieIdVanTestSubcategorie());
    }

    @Test
    public void bepaalMinimaleVerkoopprijs(){
        long voorUpdate = minimaleVerkoopprijsVanProduct();
        service.bepaalMinimaleVerkoopprijs(idVanTestProduct());
        long naUpdate = minimaleVerkoopprijsVanProduct();
        assertNotEquals(voorUpdate,naUpdate);
    }
}
