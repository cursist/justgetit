package be.vdab.justgetit.repository;

import be.vdab.justgetit.domain.Merk;
import be.vdab.justgetit.domain.Product;
import be.vdab.justgetit.domain.Subcategorie;
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
}