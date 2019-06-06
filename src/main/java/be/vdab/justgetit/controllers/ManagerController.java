package be.vdab.justgetit.controllers;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.entities.SubcategorieEigenschap;
import be.vdab.justgetit.services.ManagerService;
import be.vdab.justgetit.forms.MargeWijziging;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("manager")
public class ManagerController {
    private static final String SUBCATEGORIEWIJZIGING = "subcategorieWijziging";
    private static final String MERKWIJZIGING = "merkWijziging";
    private final ManagerService service;
    private String categorieNaamKeuze = "";
    private Subcategorie subcategorieKeuze = null;
    private long subcategorieId;
    private BigDecimal bedragSubcategorie;
    private BigDecimal percentSubcategorie;
    private String margeCatKeuze="";
    private Subcategorie subcatKeuze;
    private Merk merkKeuze;
    private long merkId;
    private BigDecimal percentMerk;
    private BigDecimal bedragMerk;
    public ManagerController(ManagerService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView pagina() {
        return new ModelAndView("manager");
    }

    @GetMapping("nieuwecategorie")
    ModelAndView categorie() {
        return new ModelAndView("nieuwecategorie")
                .addObject("categorieen", service.vindAlleCategorieen())
                .addObject(new Categorie(null));
    }

    @PostMapping("nieuwecategorie")
    ModelAndView maakNieuweCategorie(@Valid Categorie categorie, Errors errors) {
        if (errors.hasErrors()) {
            return categorie()
                    .addObject(categorie);
        } else {
            System.out.println(categorie.getNaam());
            service.save(categorie);
            return categorie();
        }
    }

    @GetMapping("nieuwesubcategorie")
    ModelAndView subcategorie() {

        return new ModelAndView("nieuwesubcategorie")
                .addObject("categorieen", service.vindAlleCategorieen())
                .addObject("subcategorieen", service.vindAlleSubCategorieen())
                .addObject(new Subcategorie(null, new Categorie(categorieNaamKeuze)));
    }

    @PostMapping("nieuwesubcategorie")
    ModelAndView maakNieuweSubCategorie(@Valid Subcategorie subcategorie, Errors errors) {
        if (errors.hasErrors()) {
            return subcategorie()
                    .addObject(subcategorie);
        } else {
            this.categorieNaamKeuze = subcategorie.getCategorie().getNaam();
            System.out.println(subcategorie.getNaam());
            service.save(subcategorie);
            return subcategorie();
        }
    }

    @GetMapping("nieuwesubcategorieeigenschap")
    ModelAndView subcategorieeigenschap() {
        return new ModelAndView("nieuwesubcategorieeigenschap")
                .addObject("subcategorieen", service.vindAlleSubCategorieen())
                .addObject("subcategorieeigenschappen", service.vindAlleEigenschappen())
                .addObject(new SubcategorieEigenschap(null, subcategorieKeuze ));
    }

    @PostMapping("nieuwesubcategorieeigenschap")
    ModelAndView maakNieuweEigenschap(@Valid SubcategorieEigenschap subcategorieEigenschap, Errors errors) {
        if (errors.hasErrors()) {
            return subcategorieeigenschap()
                    .addObject(subcategorieEigenschap);
        } else {
            this.subcategorieKeuze = subcategorieEigenschap.getSubcategorie();
            service.save(subcategorieEigenschap);
            return subcategorieeigenschap();
        }

    }

    @GetMapping("voegmerktoe")
    ModelAndView voegmerktoe() {
        return new ModelAndView("voegmerktoe")
                .addObject(new Merk(null))
                .addObject("merken", service.vindAlleMerken());
    }

    @PostMapping("voegmerktoe")
    ModelAndView voegmerktoe(@Valid Merk merk, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(merk.getNaam() + "fout");
            return voegmerktoe()
                    .addObject(merk);
        } else {
            System.out.println(merk.getNaam());
            service.save(merk);
            return voegmerktoe();
        }
    }

    @GetMapping("voegmargetoeaansubcategorie")
    ModelAndView voegMargeToeSubcategorie() {
        return new ModelAndView("voegmargetoeaansubcategorie")
                .addObject("subcategorieen", service.vindAlleSubCategorieen())
                .addObject("categorieen", service.vindAlleCategorieen())
                .addObject("subcatKeuze", subcatKeuze)
                .addObject(new Subcategorie(null, new Categorie("")))
                .addObject(SUBCATEGORIEWIJZIGING,
                        new MargeWijziging(subcategorieId, percentSubcategorie, bedragSubcategorie));
    }

    @PostMapping("voegmargetoeaansubcategorie")
    ModelAndView kiesSubcategorieVoorMarge(@Valid @ModelAttribute(SUBCATEGORIEWIJZIGING) MargeWijziging wijziging,@Valid long id, Errors errors) {
        if (errors.hasErrors()) {
            return voegMargeToeSubcategorie();
        } else {
                Subcategorie subc = service.findSubcategorieById(id).get();
                subcategorieId = subc.getId();
                percentSubcategorie = subc.getMinimumMargePercent();
                bedragSubcategorie = subc.getMinimumMargeBedrag();
                this.subcatKeuze = subc;
            return voegMargeToeSubcategorie();
        }
    }


    @PostMapping("voegmargetoeaansubcategorie/wijzig")
    ModelAndView voegMargeToeAanSubcategorie(@ModelAttribute(SUBCATEGORIEWIJZIGING) MargeWijziging wijziging, Errors errors) {
        if (errors.hasErrors()) {
            return voegMargeToeSubcategorie()
                    .addObject(SUBCATEGORIEWIJZIGING, wijziging);
        } else {
           MargeWijziging mw = new MargeWijziging(subcatKeuze.getId(),
                    wijziging.getMinimumMargePercent(),wijziging.getMinimumMargeBedrag());
            service.pasSubcategorieMargeAan(mw);
            subcategorieId = 0L;
            percentSubcategorie = null;
            bedragSubcategorie = null;
            subcatKeuze = null;

            return voegMargeToeSubcategorie();
        }

    }

    @GetMapping("voegmargetoeaanmerk")
    ModelAndView voegMargeToeAanMerk() {
        return new ModelAndView("voegmargetoeaanmerk")
                .addObject("merken", service.vindAlleMerken())
                .addObject("merkKeuze", merkKeuze)
                .addObject(new Merk(null))
                .addObject(MERKWIJZIGING, new MargeWijziging(merkId, percentMerk, bedragMerk));
    }

    @PostMapping("voegmargetoeaanmerk")
    ModelAndView voegMargeToeAanMerk( @ModelAttribute(MERKWIJZIGING) MargeWijziging wijziging, @Valid long id, Errors errors) {
        if (errors.hasErrors()) {
            return voegMargeToeAanMerk();
        } else {
            Merk mk = service.findMerkById(id).get();
            merkId = mk.getId();
            percentMerk = mk.getMinimumMargePercent();
            bedragMerk = mk.getMinimumMargeBedrag();
            this.merkKeuze = mk;
            return voegMargeToeAanMerk();
        }
    }


    @PostMapping("voegmargetoeaanmerk/wijzig")
    ModelAndView voegMargeToeAanMerk(@ModelAttribute(MERKWIJZIGING) MargeWijziging wijziging, Errors errors) {
        if (errors.hasErrors()) {
            return voegMargeToeAanMerk()
            .addObject(MERKWIJZIGING, wijziging);
        }else{
            MargeWijziging mw = new MargeWijziging(merkKeuze.getId(),wijziging.getMinimumMargePercent(),wijziging.getMinimumMargeBedrag());
            service.pasMerkMargeAan(mw);
            merkId = 0L;
            percentMerk = null;
            bedragMerk = null;
            merkKeuze = null;
            return voegMargeToeAanMerk();
        }



    }

}
