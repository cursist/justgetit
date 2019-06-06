package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol,Long> {
    Rol findByNaam(String naam);
}
