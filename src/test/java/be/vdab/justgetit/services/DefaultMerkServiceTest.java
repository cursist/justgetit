package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.repositories.MerkRepository;
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
public class DefaultMerkServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    private DefaultMerkService service;
    private Merk merk;

    @Mock
    private MerkRepository repo;

    @Before
    public void before() {
        service = new DefaultMerkService(repo);
        merk = new Merk("TestMerk");
        when(repo.findById(1L)).thenReturn(Optional.of(merk));
        when(repo.findByNaamLike("TestMerk")).thenReturn(Optional.of(merk));
    }

    @Test
    public void findById() {
        assertEquals("TestMerk", service.findById(1).get().getNaam());
    }

    @Test
    public void findByNaamLike() {
        assertEquals("TestMerk", service.findByNaamLike("TestMerk").get().getNaam());
    }
}
