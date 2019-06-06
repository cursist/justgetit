package be.vdab.justgetit.controllers;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.forms.AantalForm;
import be.vdab.justgetit.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    ModelAndView producten(){
        List<Product> producten = productService.findAll();
        return new ModelAndView("producten").addObject("producten",producten);
    }

    @GetMapping("{id}")
    ModelAndView product(@PathVariable long id){
        Product product = productService.findById(id).get();
        System.out.println(product.getNaam());
        return new ModelAndView("product").addObject("product",product)
                .addObject("aantalForm",new AantalForm(null, null));
    }
}
