package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findById(long id);
    Optional<Categorie> findByNaam(String naam);
    List<Categorie> findByNaamLike(String naam);

}
