package be.vdab.justgetit.controllers;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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


