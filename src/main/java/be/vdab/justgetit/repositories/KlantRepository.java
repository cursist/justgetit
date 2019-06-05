package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Klant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KlantRepository extends JpaRepository<Klant, Long> {

    List<Klant> findByGemeenteStartingWith(String string);

    List<Klant> findByGemeentePostcodeOrderByGemeentePostcode(String string);

    List<Klant> findByVoornaamContaining(String string);


}
