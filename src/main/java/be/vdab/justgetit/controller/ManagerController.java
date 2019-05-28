package be.vdab.justgetit.controller;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.entities.SubcategorieEigenschap;
import be.vdab.justgetit.services.ManagerService;
import be.vdab.justgetit.forms.MargeWijziging;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("nieuwecategorie")
    ModelAndView maakNieuweCategorie(@Valid Categorie categorie, Errors errors) {
        if (errors.hasErrors()) {
            return pagina()
                    .addObject(categorie);
        } else {
            System.out.println(categorie.getNaam());
            service.save(categorie);
            return pagina();
        }
    }

    @PostMapping("nieuwesubcategorie")
    ModelAndView maakNieuweSubCategorie(@Valid Subcategorie subcategorie, Errors errors) {
        if (errors.hasErrors()) {
            return pagina()
                    .addObject(subcategorie);
        } else {
            System.out.println(subcategorie.getNaam());
            service.save(subcategorie);
            return pagina();
        }
    }

    @PostMapping("nieuweeigenschap")
    ModelAndView maakNieuweEigenschap(@Valid SubcategorieEigenschap subcategorieEigenschap, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("error gevonden ");
            return pagina()
                    .addObject(subcategorieEigenschap);
        } else {
            System.out.println(subcategorieEigenschap.getNaam());
            service.save(subcategorieEigenschap);
            return pagina();
        }

    }

    @PostMapping("voegmargetoeaansubcategorie")
    ModelAndView voegMargeToeAanSubcategorie(@Valid @ModelAttribute(SUBCATEGORIEWIJZIGING) MargeWijziging wijziging, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("fout met bigdecimal");
            return pagina()
                    .addObject(SUBCATEGORIEWIJZIGING, wijziging);
        } else {
            service.pasSubCategorieMargeAan(wijziging);
            return pagina();
        }

    }

    @PostMapping("voegmargetoeaanmerk")
    ModelAndView voegMargeToeAanMerk(@Valid @ModelAttribute(MERKWIJZIGING) MargeWijziging wijziging, Errors errors) {
        if (errors.hasErrors()) {
            return pagina()
                    .addObject(MERKWIJZIGING, wijziging);
        } else {
            service.pasMerkMargeAan(wijziging);
            return pagina();
        }
    }

}
