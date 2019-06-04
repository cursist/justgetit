package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findByVerkoopprijsBetween(BigDecimal van , BigDecimal tot);
    List<Product> findByBesteldIsNull();

    List<Product> findByVoorraadLessThan(int aantal);
    List<Product>findByNaamContaining(String zoekString);
}