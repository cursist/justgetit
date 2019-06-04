package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.entities.SubcategorieEigenschap;
import be.vdab.justgetit.forms.MargeWijziging;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class ManagerServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    private RowMapper<Merk> merkMapper =
            (resultSet, i) -> new Merk(
                    resultSet.getLong("merkId"),
                    resultSet.getString("naam"),
                    resultSet.getBigDecimal("minimumMargePercent"),
                    resultSet.getBigDecimal("minimumMargeBedrag")
            );

    @Autowired private ManagerService service;
    @Autowired private EntityManager manager;
    private Categorie categorie;
    private Subcategorie subcategorie;
    private SubcategorieEigenschap subcategorieEigenschap;
    private Merk merk;

    @Before
    public void setUp() {
        categorie = new Categorie("unieke categorie");
        subcategorie = new Subcategorie("unieke subcategorie",categorie);
        subcategorieEigenschap = new SubcategorieEigenschap("unieke subcategorieEigenschapp",subcategorie);
        merk = new Merk("uniek merk");
    }

    @Test
    public void saveCategorie() {
        int aantalCategorieenVooraf = super.countRowsInTable("categorieen");
        service.save(categorie);
        int aantalCategorieenAchteraf = super.countRowsInTable("categorieen");
        assertEquals(aantalCategorieenAchteraf, aantalCategorieenVooraf + 1);
    }

    @Test
    public void saveSubcategorie() {
        int aantalSubcategorieenVooraf = super.countRowsInTable("subcategorieen");
        service.save(categorie);
        service.save(subcategorie);
        int aantalSubcategorieenAchteraf = super.countRowsInTable("subcategorieen");
        assertEquals(aantalSubcategorieenAchteraf, aantalSubcategorieenVooraf + 1);
    }

    @Test
    public void saveSubcategorieEigenschap() {
        int aantalSubcategorieEigenschappenVooraf = super.countRowsInTable("subcategorieEigenschappen");
        service.save(categorie);
        service.save(subcategorie);
        service.save(subcategorieEigenschap);
        int aantalSubcategorieEigenschappenAchteraf = super.countRowsInTable("subcategorieEigenschappen");
        assertEquals(aantalSubcategorieEigenschappenAchteraf, aantalSubcategorieEigenschappenVooraf + 1);
    }

    @Test
    public void pasSubcategorieMargeAan() {
        service.save(categorie);
        service.save(subcategorie);
        String query = "select subcategorieId from subcategorieen where naam = 'unieke subcategorie'";
        long id = super.jdbcTemplate.queryForObject(query, Long.class);
        BigDecimal marge = BigDecimal.TEN;
        MargeWijziging wijziging = new MargeWijziging(id, marge, null);
        service.pasSubcategorieMargeAan(wijziging);
        assertEquals(marge, subcategorie.getMinimumMargePercent());
    }

    @Test
    public void pasMerkAan() {
        String query = "select merkId, naam, minimumMargePercent, minimumMargeBedrag from merken limit 1";
        Merk merkEerst = super.jdbcTemplate.queryForObject(query, merkMapper);
        long id = merkEerst.getId();
        BigDecimal marge = BigDecimal.ONE;
        if (merkEerst.getMinimumMargePercent() != null) {
            marge = merkEerst.getMinimumMargePercent().add(BigDecimal.ONE);
        }
        MargeWijziging wijziging = new MargeWijziging(merkEerst.getId(), marge, null);
        service.pasMerkMargeAan(wijziging);
        manager.flush();
        String query2 = "select merkId, naam, minimumMargePercent, minimumMargeBedrag from merken where merkId = ?";
        Merk merkVervolgens = super.jdbcTemplate.queryForObject(query2, merkMapper, id);
        assertEquals(marge, merkVervolgens.getMinimumMargePercent());
    }
}