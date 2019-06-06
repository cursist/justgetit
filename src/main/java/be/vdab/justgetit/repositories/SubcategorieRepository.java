package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Subcategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubcategorieRepository extends JpaRepository<Subcategorie,Long> {

    List<Subcategorie> findByCategorieCategorieId(long id);

    Optional<Subcategorie> findByNaam(String str);

    List<Subcategorie> findByNaamLike(String str);

    Optional<Subcategorie> findById(long id);


    @Query("select s.subcategorie from SubcategorieEigenschap s where s.naam = :str")
    List<Subcategorie> findByEigenschappenNaamLike(String str);

}
