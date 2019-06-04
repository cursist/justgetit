package be.vdab.justgetit.controllers;

import be.vdab.justgetit.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

@RequestMapping("product")
public class ProductController {


    private ProductService productService;

    public ProductController( ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{productId}")
    ModelAndView productDetail(@PathVariable int productId) {
        ModelAndView modelAndView = new ModelAndView("product");
        productService.findById(productId).ifPresent(product -> {
            modelAndView.addObject(product);
        });
        return modelAndView;
    }

}
