package be.vdab.justgetit.manager.controller;

import be.vdab.justgetit.domain.Categorie;
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
                .addObject("categorie", new Categorie("tonen lukt"))
                .addObject("categorieLijst", service.vindAlleCategorieen())
                .addObject("subcategorieLijst", getCategorieen())
                .addObject("merkLijst", getCategorieen());
    }

    @GetMapping("nieuwecategorie")
    ModelAndView maakNieuweCategorie(Categorie categorie) {
        service.save(categorie);
        return pagina();
    }

    List<String> getCategorieen() {
        List<String> lijst = new ArrayList<>();
        lijst.add("test1");
        lijst.add("test");
        return lijst;
    }

}
