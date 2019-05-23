package be.vdab.justgetit.service;

import be.vdab.justgetit.domain.Merk;
import be.vdab.justgetit.domain.Product;
import be.vdab.justgetit.domain.Subcategorie;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(long productId);
    List<Merk> findByMerkId(long merkId);
    List<Subcategorie> findBySubcategorieId(long subcategorieId);
    List<Product> findByVerkoopprijsBetween(BigDecimal van , BigDecimal tot);
    List<Product> findByBesteldIsNull();
    List<Product> findByVoorraadKleinerDan(BigDecimal aantal);
}
