package be.vdab.justgetit.bediende.controller;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.services.BediendeService;
import be.vdab.justgetit.services.ProductService;
import be.vdab.justgetit.services.SubcategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("bediende")
public class BediendeController {

    private final BediendeService bediendeService;
    private final ProductService productService;
    private final SubcategorieService subcatService;

    public BediendeController(BediendeService bediendeService, ProductService productService, SubcategorieService subcatService) {
        this.bediendeService = bediendeService;
        this.productService = productService;
        this.subcatService = subcatService;
    }

    @GetMapping
    ModelAndView bediendeModelAndView() {
/*        List <Product> product= productService.findAll();*/
        ModelAndView modelAndView = new ModelAndView("bediende");
        modelAndView.addObject("productenlijst", productService.findAll());
        modelAndView.addObject("subcatlijst", subcatService.findAll());
        return modelAndView;
    }


}
