package be.vdab.justgetit.manager.controller;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.entities.SubcategorieEigenschap;
import be.vdab.justgetit.manager.ManagerService;
import be.vdab.justgetit.manager.forms.MargeWijziging;
import be.vdab.justgetit.services.SubcategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("manager")
public class ManagerController {
    private final ManagerService service;
    private final SubcategorieService subcategorieService;

    public ManagerController(ManagerService service, SubcategorieService subcategorieService) {
        this.service = service;
        this.subcategorieService = subcategorieService;
    }

    @GetMapping
    ModelAndView pagina() {
        MargeWijziging subcategorieWijziging = new MargeWijziging(null, null, null);
        MargeWijziging merkWijziging = new MargeWijziging(null, null, null);
        return paginaMetWijzigingen(merkWijziging, subcategorieWijziging);
    }

    private ModelAndView paginaMetWijzigingen(MargeWijziging subcategoriewijziging, MargeWijziging merkwijziging) {
        return new ModelAndView("manager")
                .addObject("categorie", new Categorie("categorie"))
                .addObject("subcategorie", new Subcategorie("subcategorie", null))
                .addObject("merk", new Merk("merk"))
                .addObject("categorieLijst", service.vindAlleCategorieen())
                .addObject("subcategorieLijst", service.vindAlleSubCategorieen())
                .addObject("merkLijst", service.vindAlleMerken())
                .addObject("merkwijziging", merkwijziging)
                .addObject("subcategorieeigenschap", new SubcategorieEigenschap(null, null))
                .addObject("subcategoriewijziging", subcategoriewijziging);
    }

    @PostMapping("nieuwecategorie")
    ModelAndView maakNieuweCategorie(Categorie categorie) {
        System.out.println(categorie.getNaam());
        service.save(categorie);
        return pagina();
    }


    @PostMapping("nieuwesubcategorie")
    ModelAndView maakNieuweSubCategorie(Subcategorie subcategorie) {
        System.out.println(subcategorie.getNaam());
        service.save(subcategorie);
        return pagina();
    }

    @PostMapping("nieuweeigenschap")
    ModelAndView maakNieuweEigenschap(SubcategorieEigenschap subcategorieEigenschap) {
        System.out.println(subcategorieEigenschap.getNaam());
        service.save(subcategorieEigenschap);
        return pagina();
    }

    @PostMapping("voegmargetoeaansubcategorie")
    ModelAndView voegMargeToeAanSubcategorie(@Valid MargeWijziging wijziging, Errors errors) {
        if (errors.hasErrors()) {
            MargeWijziging merkWijziging = new MargeWijziging(null, null, null);
            return paginaMetWijzigingen(merkWijziging, wijziging); //werkt nog niet;
        } else {
            service.pasSubCategorieMargeAan(wijziging);
        }
        return pagina();
    }

    @PostMapping("voegmargetoeaanmerk")
    ModelAndView voegMargeToeAanMerk(@Valid MargeWijziging wijziging, Errors errors) {
        if (errors.hasErrors()) {
            MargeWijziging subcategorieWijziging = new MargeWijziging(null, null, null);
            return paginaMetWijzigingen(wijziging, subcategorieWijziging); //werkt nog niet;
        } else {
            service.pasMerkMargeAan(wijziging);
        }
        return pagina();
    }

    List<String> getCategorieen() {
        List<String> lijst = new ArrayList<>();
        lijst.add("test1");
        lijst.add("test");
        return lijst;
    }

}
