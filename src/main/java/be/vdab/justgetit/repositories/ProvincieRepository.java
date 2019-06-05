package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Land;
import be.vdab.justgetit.entities.Provincie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvincieRepository extends JpaRepository<Provincie, Long> {

}
