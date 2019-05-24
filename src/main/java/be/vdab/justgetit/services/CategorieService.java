package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> findByNaamLike(String naam);
}
