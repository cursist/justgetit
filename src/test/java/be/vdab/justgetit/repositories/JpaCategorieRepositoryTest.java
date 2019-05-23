package be.vdab.justgetit.repositories;

import be.vdab.justgetit.domain.Categorie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



        @RunWith(SpringRunner.class)
        @DataJpaTest
        @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
        @Sql("/findCategorie.sql")
        @Import(JpaCategorieRepositoryTest.class)
        public class JpaCategorieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

        @Autowired
        private JpaCategorieRepositoryTest repository;
        private long idVanTestCat () {
        return super.jdbcTemplate.queryForObject(
                "select id from categorieen where naam = 'testC'", Long.class);
    }
    private String naamVanTestCat() {
            return super.jdbcTemplate.queryForObject(
                    "select naam from categorieen where id = 500", String.class);

    }

            @Test
            public void findById() {
              Categorie categorie   = repository.findById(idVanTestCat()).get();
                assertEquals("testM", categorie.getNaam());
            }
            @Test
            public void findByOnbestaandeId() {
                assertFalse(repository.findById(-1).isPresent());
            }

            @Test
            public void findByNaam(){
                Categorie categorie = repository.findByNaam(naamVanTestCat()).get();
                assertEquals("testC", categorie.getNaam());

            }
            @Test
            public void findByOnbestaandeNaam(){
                Categorie categorie = repository.findByNaam("blabla").get();
                assertEquals("testC", categorie.getNaam());


        }
}
