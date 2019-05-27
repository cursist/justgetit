package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(long productId);
    List<Merk> findByMerkId(long merkId);
    List<Product> findBySubcategorieId(long subcategorieId);
    List<Product> findByVerkoopprijsBetween(BigDecimal van , BigDecimal tot);
    List<Product> findByBesteldIsNull();
    List<Product> findByVoorraadKleinerDan(BigDecimal aantal);
    Product save(Product product);
    void delete(Product product);
    List<Product>findByNaamContaining(String zoekString);
}
