package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Gemeente;
import be.vdab.justgetit.entities.Klant;
import be.vdab.justgetit.entities.Land;
import be.vdab.justgetit.entities.Provincie;
import be.vdab.justgetit.entities.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class AccountServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    AccountService service;

    private Klant klant;
    private Gemeente gemeente;
    private Provincie provincie;
    private Land land;
    private Account account;

    @Before
    public void setUp(){
        land = new Land("TestLand");
        provincie = new Provincie("TestProv",land);
        gemeente = new Gemeente("TestGem","6969",provincie);
        klant = new Klant("test","tester","teststraat","69696969", "test@test.test",gemeente);
        klant.addAccount("testAccountNaam", "wachtwoord");
    }

    @Test
    public void save() {
        int aantalKlantenVooraf = super.countRowsInTable("klanten");
        service.save(klant);
        int aantalKlantenAchteraf = super.countRowsInTable("klanten");
        assertEquals(aantalKlantenAchteraf, aantalKlantenVooraf + 1);
    }
}
