package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Merk;
import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Merk> findByMerkId(long merkId);
    List<Subcategorie> findBySubcategorieId(long subcategorieId);
    List<Product> findByVerkoopprijsBetween(BigDecimal van , BigDecimal tot);
    List<Product> findByBesteldIsNull();
    List<Product> findByVoorraadKleinerDan(@Param("aantal") BigDecimal aantal);
    List<Product>findByNaamContaining(String zoekString);
}