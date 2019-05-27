package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(long productId);
    List<Product> findByVerkoopprijsBetween(BigDecimal van , BigDecimal tot);
    List<Product> findByBesteldIsNull();
    List<Product> findByVoorraadKleinerDan(BigDecimal aantal);
    Product save(Product product);
    void delete(Product product);
    List<Product>findByNaamContaining(String zoekString);
}
