package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.entities.SubcategorieEigenschap;
import be.vdab.justgetit.forms.MargeWijziging;
import be.vdab.justgetit.repositories.CategorieRepository;
import be.vdab.justgetit.repositories.MerkRepository;
import be.vdab.justgetit.repositories.SubcategorieEigenschapRepository;
import be.vdab.justgetit.repositories.SubcategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Isolation.*;

@Service
@Transactional(readOnly = false, isolation = READ_COMMITTED)
public class ManagerService {
    private final CategorieRepository categorieRepository;
    private final SubcategorieRepository subcategorieRepository;
    private final MerkRepository merkRepository;
    private final SubcategorieEigenschapRepository subcategorieEigenschapRepository;

    public ManagerService(CategorieRepository categorieRepository, SubcategorieRepository subcategorieRepository, MerkRepository merkRepository, SubcategorieEigenschapRepository subcategorieEigenschapRepository) {
        this.categorieRepository = categorieRepository;
        this.subcategorieRepository = subcategorieRepository;
        this.merkRepository = merkRepository;
        this.subcategorieEigenschapRepository = subcategorieEigenschapRepository;
    }

    @Transactional(readOnly = true, isolation = READ_COMMITTED)
    public List<Categorie> vindAlleCategorieen() {
        return categorieRepository.findAll();
    }

    public void save(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    public void save(Subcategorie subcategorie) {
        subcategorieRepository.save(subcategorie);
    }

    public void save(SubcategorieEigenschap subcategorieEigenschap) {
        subcategorieEigenschapRepository.save(subcategorieEigenschap);
    }

    public Merk save(Merk merk) {
        return merkRepository.save(merk);
    }

    @Transactional(readOnly = true, isolation = READ_COMMITTED)
    public List<Subcategorie> vindAlleSubCategorieen() {
        return subcategorieRepository.findAll();
    }

    @Transactional(readOnly = true, isolation = READ_COMMITTED)
    public List<Merk> vindAlleMerken() {
        return merkRepository.findAll();
    }

    public void pasSubcategorieMargeAan(MargeWijziging wijziging) {
        long id = wijziging.getId();
        Optional<Subcategorie> optionalSubcategorie = subcategorieRepository.findById(id);
        if (optionalSubcategorie.isPresent()) {
            Subcategorie subcategorie = optionalSubcategorie.get();
            BigDecimal minimumMargeBedrag = wijziging.getMinimumMargeBedrag();
            BigDecimal minimumMargePercent = wijziging.getMinimumMargePercent();
            if (minimumMargeBedrag !=null) {
                subcategorie.setMinimumMargeBedrag(minimumMargeBedrag);
            }
            if (minimumMargePercent !=null) {
                subcategorie.setMinimumMargePercent(minimumMargePercent);
            }
        } else {
            throw new RuntimeException("subcategorie niet gevonden");
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
        } else {
            throw new RuntimeException("merk niet gevonden");
        }
    }
}
