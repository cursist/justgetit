package be.vdab.justgetit.repositories;


import be.vdab.justgetit.entities.Categorie;

<<<<<<< .merge_file_a11968
import java.util.Optional;

public interface CategorieRepository {
    Optional<Categorie> findById(long categorieId);
    Optional<Categorie> findByNaam(String naam);
=======
import java.util.List;
import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findById(long id);
    Optional<Categorie> findByNaam(String naam);
    List<Categorie> findByNaamLike(String naam);

>>>>>>> .merge_file_a03752
}
