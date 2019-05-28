package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.services.DefaultCategorieService;
import be.vdab.justgetit.services.DefaultMerkService;
import be.vdab.justgetit.services.DefaultSubcategorieService;
import be.vdab.justgetit.services.SubcategorieService;
import org.junit.Before;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/insertCategorie.sql")
@Sql("/insertSubCategorie.sql")
@Sql("/insertMerk.sql")

@Import(JpaBediendeRepository.class)
public class JpaBediendeRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JpaBediendeRepository repo;
    @Autowired
    private EntityManager manager;
    @Autowired
    private DefaultSubcategorieService defSubCatService;
    @Autowired
    private DefaultMerkService defMerkService;


    private Product product;
    private Merk merk;
    private Subcategorie subcategorie;
    private Subcategorie subcat2;

    private long idVanSubcategorie(){
        return super.jdbcTemplate.queryForObject("select subcategorieId from subcategorieen where naam = 'TestSubCategorie'", Long.class);
    }

    private long idVanMerk(){
        return super.jdbcTemplate.queryForObject("select merkId from merken where naam = 'testM'", Long.class);
    }

    @Before
    public void before (){
        subcategorie = defSubCatService.findByNaam("TestSubCategorie");
        subcat2 = defSubCatService.findByNaam("Test2SubCategorie");
        merk = defMerkService.findByNaamLike("testM").get();
        product = new Product ("testproduct", BigDecimal.valueOf(9999), BigDecimal.ONE, BigDecimal.ONE,
                1,1, merk, subcategorie);
    }

    @Test
    public void zetProductInSubcategorie(){
        repo.zetProductInSubCategorie(product,subcat2);
        assertEquals(product.getSubcategorie().getId(), subcat2.getId());
    }
}
