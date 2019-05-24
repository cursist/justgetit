package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Subcategorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategorieRepository extends JpaRepository<Subcategorie,Long> {

    List<Subcategorie> findByCategorieCategorieId(long id);

    Subcategorie findByNaam(String str);

    List<Subcategorie> findByNaamLike(String str);
}
