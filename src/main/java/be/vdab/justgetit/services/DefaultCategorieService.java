package be.vdab.justgetit.services;


import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.repositories.CategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
public class DefaultCategorieService implements CategorieService {


    private final CategorieRepository categorieRepository;

    public DefaultCategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Optional<Categorie> findById(long id) {
       return categorieRepository.findById(id);
    }

    @Override
    public Optional<Categorie> findByNaam(String naam) {
        return categorieRepository.findByNaam(naam);
    }
}
