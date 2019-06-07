package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Gemeente;
import be.vdab.justgetit.entities.Provincie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GemeenteRepository extends JpaRepository<Gemeente, Long> {

    boolean existsByNaam(String naam);

    Gemeente findByNaam(String naam);
}
