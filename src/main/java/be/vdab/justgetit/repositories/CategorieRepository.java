package be.vdab.justgetit.repositories;


import be.vdab.justgetit.entities.Categorie;

import java.util.Optional;

public interface CategorieRepository {
    Optional<Categorie> findById(long categorieId);
    Optional<Categorie> findByNaam(String naam);
}
