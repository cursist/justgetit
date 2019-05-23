package be.vdab.justgetit.repositories;

import be.vdab.justgetit.domain.Categorie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaCategorieRepositoryTest implements CategorieRepository {
    private final EntityManager manager;
    JpaCategorieRepositoryTest(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public Optional<Categorie> findById(long id) {
        return Optional.ofNullable(manager.find(Categorie.class, id));
    }
    @Override
    public Optional<Categorie> findByNaam(String naam) {
        return Optional.ofNullable(manager.find(Categorie.class, naam));
    }
}
