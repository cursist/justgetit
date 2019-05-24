package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Categorie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/findCategorie.sql")
public class JpaCategorieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CategorieRepository repository;
    private Categorie categorie;

    private long idVanTestCat() {
        return super.jdbcTemplate.queryForObject(
                "select categorieId from categorieen where naam = 'testC'", Long.class);
    }

    private String naamVanTestCat(long id) {
        return super.jdbcTemplate.queryForObject(
                "select naam from categorieen where categorieId = ?", String.class, id);

    }


    @Test
    public void findById() {
        Categorie categorie = repository.findById(idVanTestCat()).get();
        assertEquals("testC", categorie.getNaam());
    }

    @Test
    public void findByOnbestaandeId() {
        assertFalse(repository.findById(-1).isPresent());
    }

    @Test
    public void findByNaam() {
        Categorie categorie = repository.findByNaam("testC").get();
        assertEquals("testC", categorie.getNaam());
    }

    @Test(expected = NoResultException.class)
    public void findByOnbestaandeNaam() {
        assertEquals(null, repository.findByNaam("bla"));
    }
}

