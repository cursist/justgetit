package be.vdab.justgetit.controllers;

import be.vdab.justgetit.forms.AantalForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mandje")
public class MandjeController {

    @GetMapping
    ModelAndView toonMandje () {
        return new ModelAndView("mandje", "mandje", new AantalForm(null));
    }
}
