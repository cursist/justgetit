package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository <CategorieRepository, Long>{
    List<Categorie> findByNaamLike(String naam);
}
