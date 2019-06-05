package be.vdab.justgetit.controllers;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.forms.AantalForm;
import be.vdab.justgetit.forms.MandjeForm;
import be.vdab.justgetit.services.ProductService;
import be.vdab.justgetit.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final ProductService productService;
    private Mandje mandje;

    public MandjeController(ProductService productService) {
        this.productService = productService;
        this.mandje = new Mandje();
    }

    @GetMapping
    ModelAndView mandje(){
        MandjeForm mandjeForm = new MandjeForm();
        mandje.getMandje().forEach((id, aantal) -> {
            mandjeForm.put(productService.findById(id).get(), aantal);
        });
        return new ModelAndView("mandje")
                .addObject("mandjeForm", mandjeForm)
                .addObject("newForm", new MandjeForm());
    }

    @GetMapping("aantal")
    ModelAndView voegToe(@Valid AantalForm form, @RequestParam long id, Errors errors,
                         RedirectAttributes redirect){
        if(errors.hasErrors()){
            return new ModelAndView();
        }
        mandje.put(id, form.getAantal());

        return new ModelAndView("redirect:/mandje");
    }

    @GetMapping("/bestelling")
    ModelAndView mandjeLijnenTonen (Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView();
        }
        Map<Long, Integer> bestelling = new LinkedHashMap<>();
        ModelAndView modelAndView = new ModelAndView("bestelling");
        mandje.getMandje().forEach((id, aantal) -> {
            bestelling.put(id, aantal);
        });
        modelAndView.addObject("bestelling", bestelling);
        return modelAndView;
    }
}
