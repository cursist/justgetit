package be.vdab.justgetit.manager.controller;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.manager.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("manager")
public class ManagerController {
    private final ManagerService service;

    public ManagerController(ManagerService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView pagina() {
        return new ModelAndView("manager")
                .addObject("categorie", new Categorie("categorie"))
                .addObject("subcategorie", new Subcategorie("subcategorie", null))
                .addObject("merk", new Merk("merk"))
                .addObject("categorieLijst", service.vindAlleCategorieen())
                .addObject("subcategorieLijst", service.vindAlleSubCategorieen())
                .addObject("merkLijst", service.vindAlleMerken());
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

    List<String> getCategorieen() {
        List<String> lijst = new ArrayList<>();
        lijst.add("test1");
        lijst.add("test");
        return lijst;
    }

}
