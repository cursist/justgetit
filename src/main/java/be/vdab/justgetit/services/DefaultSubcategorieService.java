package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.repositories.SubcategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultSubcategorieService implements SubcategorieService {
    private SubcategorieRepository subcategorieRepository;

    public DefaultSubcategorieService(SubcategorieRepository subcategorieRepository) {
        this.subcategorieRepository = subcategorieRepository;
    }

    @Override
    public List<Subcategorie> findAll(){
        return subcategorieRepository.findAll();
    }

    @Override
    public Optional<Subcategorie> findById(long id){
        return subcategorieRepository.findById(id);
    }

    @Override
    public List<Subcategorie> findByCategorieId(long id) {
        return null;
    }
}
