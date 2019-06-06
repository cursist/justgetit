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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public List<Categorie> vindAlleCategorieen() {

        return categorieRepository.findAll()
                .stream().sorted((o1, o2) -> o1.getNaam().compareToIgnoreCase(o2.getNaam()))
                .collect(Collectors.toList());
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

    public void save(Merk merk){
        merkRepository.save(merk);
    }

    public List<Subcategorie> vindAlleSubCategorieen() {
        return subcategorieRepository.findAll()
                .stream().sorted((o1, o2) -> o1.getNaam().compareToIgnoreCase(o2.getNaam()))
                .collect(Collectors.toList());
    }

    public Optional<Subcategorie> findSubcategorieById(long id){
        if (subcategorieRepository.findById(id).isPresent()) {
            return subcategorieRepository.findById(id);
        }else{
            return Optional.empty();
        }
    }

    public List<Merk> vindAlleMerken() {
        return merkRepository.findAll()
                .stream().sorted((o1, o2) -> o1.getNaam().compareToIgnoreCase(o2.getNaam()))
                .collect(Collectors.toList());
    }
    public Optional<Merk> findMerkById(long id){
        if (merkRepository.findById(id).isPresent()){
            return merkRepository.findById(id);
        }
        else {
            return Optional.empty();
        }
    }

    public List<SubcategorieEigenschap> vindAlleEigenschappen(){
        return subcategorieEigenschapRepository.findAll()
                .stream().sorted((o1, o2) -> o1.getNaam().compareToIgnoreCase(o2.getNaam()))
                .collect(Collectors.toList());
    }

    public void pasSubcategorieMargeAan(MargeWijziging wijziging) {
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
