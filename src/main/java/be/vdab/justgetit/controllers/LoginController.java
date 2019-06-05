package be.vdab.justgetit.controllers;

import be.vdab.justgetit.entities.Gemeente;
import be.vdab.justgetit.entities.Klant;
import be.vdab.justgetit.entities.Land;
import be.vdab.justgetit.entities.Provincie;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginController {
    @GetMapping
    String pagina(){
        return "login";
    }

    @GetMapping("accountmaken")
    ModelAndView accountMakenPagina() {
        return new ModelAndView("accountmaken")
                .addObject(new Klant("test","tester","teststraat","69696969","test@test.test",new Gemeente("testerst","6969",new Provincie("testtesttest", new Land("testerigst")))));
    }

    @PostMapping("accountmaken/submit")
    ModelAndView accountMaken(Klant klant) {
        System.out.println(klant.getVoornaam());
        return accountMakenPagina();
    }

    @InitBinder("klant")
    void initBinder(DataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
