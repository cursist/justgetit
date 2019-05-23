package be.vdab.justgetit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Categorie extends JpaRepository <Categorie, Long>{
}
