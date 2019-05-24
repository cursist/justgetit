package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.repositories.CategorieRepository;

import java.util.List;

public class DefaultCategorieService implements CategorieService {
    private final CategorieRepository categorieRepository;

    public DefaultCategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public List<Categorie> findByNaamLike(String naam){
        return categorieRepository.findByNaamLike(naam);
    }
}
