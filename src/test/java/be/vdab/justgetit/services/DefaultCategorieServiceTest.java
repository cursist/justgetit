package be.vdab.justgetit.services;

import be.vdab.justgetit.domain.Categorie;
import be.vdab.justgetit.repositories.JpaCategorieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;


import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCategorieServiceTest extends AbstractTransactionalJUnit4SpringContextTests {


    private DefaultCategorieService service;
    private Categorie categorie;

    @Mock
    private JpaCategorieRepository jpaCategorieRepository;

    @Before
    public void before() {
        service = new DefaultCategorieService(jpaCategorieRepository);
        categorie = new Categorie("TestCategorie");
        when(jpaCategorieRepository.findById(1)).thenReturn(Optional.of(categorie));
        when(jpaCategorieRepository.findByNaam("TestCategorie")).thenReturn(Optional.of(categorie));
    }

    @Test
    public void findById() {
        assertEquals("TestCategorie", service.findById(1).get().getNaam());
    }

    @Test
    public void findByNaam() {
        assertEquals("TestCategorie", service.findByNaam("TestCategorie").get().getNaam());
    }

}
