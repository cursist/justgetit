package be.vdab.justgetit.repositories;

<<<<<<< HEAD
import be.vdab.justgetit.domain.Categorie;

import java.util.Optional;

public interface CategorieRepository {
    Optional<Categorie> findById(long id);
    Optional<Categorie> findByNaam(String naam);

=======
import be.vdab.justgetit.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository <CategorieRepository, Long>{
    List<Categorie> findByNaamLike(String naam);
>>>>>>> d79fca2b90cfb48321ae86c7c45a3acd3b69bf32
}
