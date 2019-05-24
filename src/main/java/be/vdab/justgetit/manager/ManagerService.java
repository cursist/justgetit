package be.vdab.justgetit.manager;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.manager.forms.MargeWijziging;
import be.vdab.justgetit.repositories.CategorieRepository;
import be.vdab.justgetit.repositories.MerkRepository;
import be.vdab.justgetit.repositories.SubcategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerService {
    private final CategorieRepository categorieRepository;
    private final SubcategorieRepository subcategorieRepository;
    private final MerkRepository merkRepository;

    public ManagerService(CategorieRepository categorieRepository, SubcategorieRepository subcategorieRepository, MerkRepository merkRepository) {
        this.categorieRepository = categorieRepository;
        this.subcategorieRepository = subcategorieRepository;
        this.merkRepository = merkRepository;
    }

    public List<Categorie> vindAlleCategorieen() {
        return categorieRepository.findAll();
    }

    public void save(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    public void save(Subcategorie subcategorie) {
        subcategorieRepository.save(subcategorie);
    }

    public List<Subcategorie> vindAlleSubCategorieen() {
        return subcategorieRepository.findAll();
    }

    public List<Merk> vindAlleMerken() {
        return merkRepository.findAll();
    }

    public void pasSubCategorieMargeAan(MargeWijziging wijziging) {
        long id = wijziging.getId();
        Optional<Subcategorie> optionalSubcategorie = subcategorieRepository.findById(id);
        if (optionalSubcategorie.isPresent()) {
            Subcategorie subcategorie = optionalSubcategorie.get();
            BigDecimal minimumMargeBedrag = wijziging.getMinimumMargeBedrag();
            BigDecimal minimumMargePercent = wijziging.getMinimumMargePercent();
            if (minimumMargeBedrag !=null){
                subcategorie.setMinimumMargeBedrag(minimumMargeBedrag);
            }
            if (minimumMargePercent !=null){
                subcategorie.setMinimumMargePercent(minimumMargePercent);
            }
        }
    }

    public void pasMerkMargeAan(MargeWijziging wijziging) {
        long id = wijziging.getId();
        Optional<Merk> optionalMerk = merkRepository.findById(id);
        if (optionalMerk.isPresent()) {
            Merk merk = optionalMerk.get();
            BigDecimal minimumMargeBedrag = wijziging.getMinimumMargeBedrag();
            BigDecimal minimumMargePercent = wijziging.getMinimumMargePercent();
            if (minimumMargeBedrag !=null){
                merk.setMinimumMargeBedrag(minimumMargeBedrag);
            }
            if (minimumMargePercent !=null){
                merk.setMinimumMargePercent(minimumMargePercent);
            }
        }
    }
}
