package be.vdab.justgetit.controllers;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.forms.ProductZoekForm;
import be.vdab.justgetit.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("productZoeken")

public class ProductZoekenController {

    List<Product> gevondenProducten = new ArrayList<>();

    private ProductService productService;

    public ProductZoekenController( ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/form")
    ModelAndView productZoekForm() {
        return new ModelAndView("productZoeken").addObject(new ProductZoekForm(""));
    }
    @GetMapping
    ModelAndView productZoekForm(ProductZoekForm form) {

        List<Product> gevondenProducten = productService.findByNaamContaining(form.getZoekTerm());


        return new ModelAndView("productZoeken").addObject("gevondenProducten",gevondenProducten );
    }

}
