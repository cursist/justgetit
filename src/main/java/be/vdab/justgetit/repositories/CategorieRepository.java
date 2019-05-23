package be.vdab.justgetit.repositories;

import be.vdab.justgetit.domain.Categorie;

import java.util.Optional;

public interface CategorieRepository {
    Optional<Categorie> findById(long id);
    Optional<Categorie> findByNaam(String naam);

}
