package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
