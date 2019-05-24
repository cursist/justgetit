package be.vdab.justgetit.manager;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.manager.forms.SubcategorieMargeWijziging;
import be.vdab.justgetit.repositories.CategorieRepository;
import be.vdab.justgetit.repositories.SubcategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerService {
    private final CategorieRepository categorieRepository;
    private final SubcategorieRepository subcategorieRepository;

    public ManagerService(CategorieRepository categorieRepository, SubcategorieRepository subcategorieRepository) {
        this.categorieRepository = categorieRepository;
        this.subcategorieRepository = subcategorieRepository;
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
        List<Merk> lijst = new ArrayList<>();
        lijst.add(new Merk("test"));
        return lijst;
    }

    public void pasMargeAan(SubcategorieMargeWijziging wijziging) {
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
}
