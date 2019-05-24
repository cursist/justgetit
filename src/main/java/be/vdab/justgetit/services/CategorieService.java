package be.vdab.justgetit.services;

<<<<<<< HEAD
import be.vdab.justgetit.domain.Categorie;

import java.util.Optional;

public interface CategorieService {
    Optional<Categorie> findById(long id);
    Optional<Categorie> findByNaam(String naam);
=======
import be.vdab.justgetit.entities.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> findByNaamLike(String naam);
>>>>>>> d79fca2b90cfb48321ae86c7c45a3acd3b69bf32
}
