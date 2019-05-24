package be.vdab.justgetit.services;


import be.vdab.justgetit.entities.Categorie;

import java.util.Optional;

public interface CategorieService {
    Optional<Categorie> findById(long id);
    Optional<Categorie> findByNaam(String naam);
}
