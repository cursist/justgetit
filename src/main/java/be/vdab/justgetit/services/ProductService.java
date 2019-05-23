package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(long id);
}
