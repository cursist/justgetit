package be.vdab.justgetit.services;

<<<<<<< HEAD
import be.vdab.justgetit.domain.Categorie;
import be.vdab.justgetit.repositories.CategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
public class DefaultCategorieService implements CategorieService {

=======
import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.repositories.CategorieRepository;

import java.util.List;

public class DefaultCategorieService implements CategorieService {
>>>>>>> d79fca2b90cfb48321ae86c7c45a3acd3b69bf32
    private final CategorieRepository categorieRepository;

    public DefaultCategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
<<<<<<< HEAD
    public Optional<Categorie> findById(long id) {
       return categorieRepository.findById(id);
    }

    @Override
    public Optional<Categorie> findByNaam(String naam) {
        return categorieRepository.findByNaam(naam);
=======
    public List<Categorie> findByNaamLike(String naam){
        return categorieRepository.findByNaamLike(naam);
>>>>>>> d79fca2b90cfb48321ae86c7c45a3acd3b69bf32
    }
}
