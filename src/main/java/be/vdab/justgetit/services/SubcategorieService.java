package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.repositories.SubcategorieRepository;

import java.util.List;
import java.util.Optional;

public interface SubcategorieService {
    List<Subcategorie> findAll();
    Optional<Subcategorie> findById(long id);
    List<Subcategorie> findByCategorieId(long id);

}
