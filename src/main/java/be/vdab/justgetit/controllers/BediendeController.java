package be.vdab.justgetit.controllers;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.forms.BediendeZoekForm;
import be.vdab.justgetit.forms.BediendeZoeklijstForm;
import be.vdab.justgetit.services.BediendeService;
import be.vdab.justgetit.services.ProductService;
import be.vdab.justgetit.services.SubcategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("bediende")
public class BediendeController {
    private final ProductService productService;
    private final SubcategorieService subcatService;
    private final BediendeService bediendeService;
    private String zoekterm;
    private long productId;

    public BediendeController(ProductService productService, SubcategorieService subcatService, BediendeService bediendeService) {
        this.productService = productService;
        this.subcatService = subcatService;
        this.bediendeService = bediendeService;
    }

    @GetMapping
    ModelAndView bediendeModelAndView() {
        ModelAndView modelAndView = new ModelAndView("bediende");
        return modelAndView.addObject("bediendeProductForm", new BediendeZoekForm(""));
    }

    @PostMapping
    ModelAndView bediendeZoekterm(@Valid BediendeZoekForm bediendeZoekForm, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("bediende");
        }
        zoekterm = bediendeZoekForm.getZoekterm();
        return new ModelAndView("redirect:/bediende/bediendeproducten");
    }

    @GetMapping("/bediendeproducten")
    ModelAndView bediendeGevondenProducten () {
        ModelAndView modelAndView = new ModelAndView("bediendeproducten");
        return modelAndView
                .addObject("alleGevondenProducten", productService.findByNaamContaining(zoekterm))
                .addObject("alleGevondenSubcategorieen", subcatService.findAll())
                .addObject("bediendeZoekLijstForm", new BediendeZoeklijstForm(1L, 1L));
    }

    @PostMapping("/bediendeproductenform")
    ModelAndView bediendeGevondenProductenForm (@Valid BediendeZoeklijstForm bediendeZoeklijstForm) {
        bediendeService.zetProductInSubCategorie(bediendeZoeklijstForm.getProductFormId(), bediendeZoeklijstForm.getSubcategorieFormId());
        productId = bediendeZoeklijstForm.getProductFormId();
        return new ModelAndView("redirect:/bediende/bediendesubcategorieveranderen");
    }

    @GetMapping("/bediendesubcategorieveranderen")
    ModelAndView bediendesubcategorieverandert (){

        Product product = productService.findById(productId).get();
        ModelAndView modelAndView = new ModelAndView("bediendesubcategorieveranderen");
         modelAndView
                .addObject("aangepastproduct",product);

        productId = 0L;
        return modelAndView;
    }
}
