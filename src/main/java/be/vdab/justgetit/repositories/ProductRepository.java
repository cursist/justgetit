package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findByVerkoopprijsBetween(BigDecimal van , BigDecimal tot);
    List<Product> findByBesteldIsNull();
   // List<Product> findByVoorraadKleinerDan(@Param("aantal") BigDecimal aantal);
    List<Product> findByVoorraadLessThan(BigDecimal aantal);
    List<Product>findByNaamContaining(String zoekString);
}