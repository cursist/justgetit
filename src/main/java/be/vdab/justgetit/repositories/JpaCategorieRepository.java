package be.vdab.justgetit.repositories;

import be.vdab.justgetit.domain.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class JpaCategorieRepository implements CategorieRepository {
    private final EntityManager manager;
    JpaCategorieRepository(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public Optional<Categorie> findById(long categorieId) {
        return Optional.ofNullable(manager.find(Categorie.class, categorieId));
    }
    @Override
    public Optional<Categorie> findByNaam(String naam) {
        TypedQuery<Categorie> query = manager.createQuery("select c from Categorie c where naam = :naam", Categorie.class);
        query.setParameter("naam", naam);
        return Optional.ofNullable(query.getSingleResult());
    }

}
