package be.vdab.justgetit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Subcategorie extends JpaRepository<Subcategorie,Long> {
}
