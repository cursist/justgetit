package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Categorie;
import be.vdab.justgetit.entities.Land;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LandRepository extends JpaRepository<Land, Long> {
    boolean existsByNaam(String string);
    Land findByNaam(String naam);
}
