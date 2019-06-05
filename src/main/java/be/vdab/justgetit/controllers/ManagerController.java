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

@Controller
@RequestMapping("manager")
public class ManagerController {
    private static final String SUBCATEGORIEWIJZIGING = "subcategorieWijziging";
    private static final String MERKWIJZIGING = "merkWijziging";
    private final ManagerService service;

    public ManagerController(ManagerService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView pagina() {
        return basisPagina()
                .addObject(new Categorie(null))
                .addObject(new Subcategorie(null, null))
                .addObject(new SubcategorieEigenschap(null, null))
                .addObject(SUBCATEGORIEWIJZIGING, new MargeWijziging(null, null, null))
                .addObject(MERKWIJZIGING, new MargeWijziging(null, null, null));
    }

    private ModelAndView basisPagina() {
        return new ModelAndView("manager")
                .addObject("categorieLijst", service.vindAlleCategorieen())
                .addObject("subcategorieLijst", service.vindAlleSubCategorieen())
                .addObject("merkLijst", service.vindAlleMerken());
    }

    @GetMapping("nieuwecategorie")
    ModelAndView categorie() {
        return new ModelAndView("nieuwecategorie")
                .addObject("categorieLijst", service.vindAlleCategorieen())
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
                .addObject("categorieLijst", service.vindAlleCategorieen())
                .addObject("subcategorieLijst", service.vindAlleSubCategorieen())
                .addObject(new Subcategorie(null, null));
    }

    @PostMapping("nieuwesubcategorie")
    ModelAndView maakNieuweSubCategorie(@Valid Subcategorie subcategorie, Errors errors) {
        if (errors.hasErrors()) {
            return subcategorie()
                    .addObject(subcategorie);
        } else {
            System.out.println(subcategorie.getNaam());
            service.save(subcategorie);
            return subcategorie();
        }
    }

    @GetMapping("nieuwesubcategorieeigenschap")
    ModelAndView subcategorieeigenschap() {
        return new ModelAndView("nieuwesubcategorieeigenschap")
                .addObject("subcategorieLijst", service.vindAlleSubCategorieen())
                .addObject(new SubcategorieEigenschap(null, null));
    }

    @PostMapping("nieuwesubcategorieeigenschap")
    ModelAndView maakNieuweEigenschap(@Valid SubcategorieEigenschap subcategorieEigenschap, Errors errors) {
        if (errors.hasErrors()) {
            return subcategorieeigenschap()
                    .addObject(subcategorieEigenschap);
        } else {
            service.save(subcategorieEigenschap);
            return subcategorieeigenschap();
        }

    }

    @GetMapping("voegmerktoe")
    ModelAndView voegmerktoe() {
        return new ModelAndView("voegmerktoe")
                .addObject(new Merk("nieuw merk"))
                .addObject("merkLijst", service.vindAlleMerken());
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
                .addObject("subcategorieLijst", service.vindAlleSubCategorieen())
                .addObject(SUBCATEGORIEWIJZIGING,
                        new MargeWijziging(null, null, null));
    }

    @PostMapping("voegmargetoeaansubcategorie")
    ModelAndView voegMargeToeAanSubcategorie(@Valid @ModelAttribute(SUBCATEGORIEWIJZIGING) MargeWijziging wijziging, Errors errors) {
        if (errors.hasErrors()) {
            return voegMargeToeSubcategorie()
                    .addObject(SUBCATEGORIEWIJZIGING, wijziging);
        } else {
            service.pasSubcategorieMargeAan(wijziging);
            return voegMargeToeSubcategorie();
        }

    }

    @GetMapping("voegmargetoeaanmerk")
    ModelAndView voegMargeToeAanMerk() {
        return new ModelAndView("voegmargetoeaanmerk")
                .addObject("merkLijst", service.vindAlleMerken())
                .addObject(MERKWIJZIGING, new MargeWijziging(null, null, null));
    }

    @PostMapping("voegmargetoeaanmerk")
    ModelAndView voegMargeToeAanMerk(@Valid @ModelAttribute(MERKWIJZIGING) MargeWijziging wijziging, Errors errors) {
        if (errors.hasErrors()) {
            return voegMargeToeAanMerk()
                    .addObject(MERKWIJZIGING, wijziging);
        } else {
            service.pasMerkMargeAan(wijziging);
            return voegMargeToeAanMerk();
        }
    }

}
