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
        return subcategorieRepository.findByCategorieCategorieId(id);
    }

    @Override
    public Subcategorie findByNaam(String str) {
        return subcategorieRepository.findByNaam(str);
    }

    @Override
    public List<Subcategorie> findByNameLike(String str) {
        return subcategorieRepository.findByNaamLike(str);
    }

/*    @Override
    public List<Subcategorie> findByEigenschappenNaamLike(String str) {
        return subcategorieRepository.findByEigenschappenNaamLike(str);
    }*/

    @Override
    public void Save(Subcategorie subcategorie) {
        subcategorieRepository.save(subcategorie);
    }

    @Override
    public void Delete(Subcategorie subcategorie) {
        subcategorieRepository.delete(subcategorie);
    }
}
