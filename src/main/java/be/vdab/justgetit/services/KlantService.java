package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantService {
    List<Klant> findAll();
    Optional<Klant> findById(long klantId);
    List<Klant> findByGemeentenaamBegin(String str);
    List<Klant> findByPostcode(String str);
    List<Klant> findByVoornaamBevat(String str);
}
