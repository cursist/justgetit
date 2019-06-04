package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Merk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerkRepository extends JpaRepository<Merk,Long> {
    Optional<Merk> findByNaamLike(String naam);
}
