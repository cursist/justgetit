package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
}
